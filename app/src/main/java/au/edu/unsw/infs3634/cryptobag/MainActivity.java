package au.edu.unsw.infs3634.cryptobag;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import au.edu.unsw.infs3634.cryptobag.Entities.Coin;
import au.edu.unsw.infs3634.cryptobag.Entities.CoinDatabase;
import au.edu.unsw.infs3634.cryptobag.Entities.CoinLoreResponse;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private boolean mTwoPane;
    private CoinAdapter mAdapter;
    public CoinDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detail_container) != null) {
            mTwoPane = true;
        }

        RecyclerView mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new CoinAdapter(this, new ArrayList<Coin>(), mTwoPane);
        mRecyclerView.setAdapter(mAdapter);

        mdb = Room.databaseBuilder(getApplicationContext(), CoinDatabase.class, "coin-database").build();

        new GetCoinDBTask().execute();
        new GetCoinTask().execute();

        // Retrofit interface to parse the retreived json


        // execute network request


    }


    private class GetCoinTask extends AsyncTask<Void, Void, List<Coin>> {

        @Override
        protected List<Coin> doInBackground(Void... voids) {
            try {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.coinlore.net/").addConverterFactory(GsonConverterFactory.create()).build();

                // get serviceand all object or request
                CoinService service = retrofit.create(CoinService.class);
                Call<CoinLoreResponse> coinsCall = service.getCoins();

                Response<CoinLoreResponse> coinResonse = coinsCall.execute();
                List<Coin> coins = coinResonse.body().getData();

                mdb.coinDao().deleteAll(mdb.coinDao().getCoins().toArray(new Coin[mdb.coinDao().getCoins().size()]));
                mdb.coinDao().insertAll(coins.toArray(new Coin[mdb.coinDao().getCoins().size()]));
                return coins;

            } catch (IOException e) {
                System.out.println("Failed to do AysncTask!");
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<Coin> coins) {
            // super.onPostExecute(coins);
            mAdapter.setCoins(coins);
        }
    }

    private class GetCoinDBTask extends AsyncTask<Void, Void, List<Coin>>{

        @Override
        protected List<Coin> doInBackground(Void... voids) {
            return mdb.coinDao().getCoins();
        }

        @Override
        protected void onPostExecute(List<Coin> coins) {
            mAdapter.setCoins(coins);
        }
    }

}
