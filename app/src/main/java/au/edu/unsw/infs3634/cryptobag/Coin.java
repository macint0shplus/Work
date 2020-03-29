package au.edu.unsw.infs3634.cryptobag;

import java.util.ArrayList;

public class Coin {
    private String name;
    private String symbol;
    private double value;
    private double change1h;
    private double change24h;
    private double change7d;
    private double marketcap;
    private double volume;

    public Coin() {
    }

    public Coin(String name, String symbol, double value, double change1h, double change24h, double change7d, double marketcap, double volume) {
        this.name = name;
        this.symbol = symbol;
        this.value = value;
        this.change1h = change1h;
        this.change24h = change24h;
        this.change7d = change7d;
        this.marketcap = marketcap;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getChange1h() {
        return change1h;
    }

    public void setChange1h(double change1h) {
        this.change1h = change1h;
    }

    public double getChange24h() {
        return change24h;
    }

    public void setChange24h(double change24h) {
        this.change24h = change24h;
    }

    public double getChange7d() {
        return change7d;
    }

    public void setChange7d(double change7d) {
        this.change7d = change7d;
    }

    public double getMarketcap() {
        return marketcap;
    }

    public void setMarketcap(double marketcap) {
        this.marketcap = marketcap;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public static Coin getCoin(String id) {
        for(Coin coin : getCoins()) {
            if(coin.getSymbol().equals(id)) {
                return coin;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static ArrayList<Coin> getCoins() {
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(new Coin("Bitcoin", "BTC", 8662.99, -5.30, 0.06, 6.25, 157394075212.34, 26248451879.217194));
        coins.add(new Coin("Ethereum", "ETH", 165.69, -5.94, 0.00, 14.58, 18116094926.74, 11453091518.956093));
        coins.add(new Coin("XRP", "XRP", 0.232488, -6.10, 0.36, 8.03, 9975961452.76, 1857161424.9047709));
        coins.add(new Coin("Bitcoin Cash", "BCH", 332.50, -5.25, 0.31, 25.16, 6061849292.55, 4034762667.1342015));
        coins.add(new Coin("Bitcoin SV", "BCHSV", 274.49, 6.09, 0.98, 71.68, 5003375789.67, 2920688747.7987976));
        coins.add(new Coin("Tether", "USDT", 1.00, 0.09, -0.04, 0.29, 4051244046.05,32969047733.32528));
        coins.add(new Coin("Litecoin", "LTC", 57.11, -6.42, 0.35, 12.84, 3665038765.74, 3433599488.5887113));
        coins.add(new Coin("EOS", "EOS", 3.58, -7.21, -0.11, 12.92, 3324669063.56, 3353780327.053705));
        coins.add(new Coin("Binance Coin", "BNB", 17.20, -5.04, 0.24, 12.51, 2675482775.95, 233309183.3948947));
        coins.add(new Coin("Stellar", "XLM", 0.061529, -2.09, 1.78, 25.85, 1232939271.42, 502557303.372596));
        return coins;
    }
}
