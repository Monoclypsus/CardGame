package com.codeclan.example.cardgame;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Hand hand1 = new Hand();
    Hand hand2 = new Hand();

    Player player1 = new Player("You", hand1);
    Player player2 = new Player("Player2", hand2);

    Game game = new Game(player1, player2);

    private Suit player1DealtCardSuit;
    private Rank player1DealtCardRank;
    private int player1HandOldValue;
    private int player1HandNewValue;
    private ArrayList<Card> player1Hand;
    private ArrayList<String> hand1Details;
    private String player1CardDetails;
    private String player1EachIcon;
    private ArrayList<String> Player1AllIcons;

    private Suit player2DealtCardSuit;
    private Rank player2DealtCardRank;
    private int player2HandOldValue;
    private int player2HandNewValue;
    private ArrayList<Card> player2Hand;
    private ArrayList<String> hand2Details;
    private String player2CardDetails;
    private String player2EachIcon;
    private ArrayList<String> Player2AllIcons;
    private SharedPreferences sharedPreferences;
    private int high_score;
    private int global_high_score;


    TextView textPlayer1LatestCard;
    Button buttonPlayer1;

    TextView textPlayer2LatestCard;
    Button buttonPlayer2;

    TextView textResult;
    TextView p1score;
    TextView p2score;
    Button buttonGameResult;

//    Button buttonAnotherPlay;

    ImageView player1FirstCardImage;
    ImageView player1SecondCardImage;
    ImageView player1ThirdCardImage;
    ImageView player1FourthCardImage;
    ImageView player1FifthCardImage;
    ImageView player1SixthCardImage;

    ImageView player2FirstCardImage;
    ImageView player2SecondCardImage;
    ImageView player2ThirdCardImage;
    ImageView player2FourthCardImage;
    ImageView player2FifthCardImage;
    ImageView player2SixthCardImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getPreferences(MODE_PRIVATE);



        TextView textView = (TextView)findViewById(R.id.playerNameTxtView);
        TextView textview2 = (TextView)findViewById(R.id.p1_name_txt_view);
        TextView highScoreView = (TextView)findViewById(R.id.highScoreTxtView);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        high_score = sharedPreferences.getInt(name, 0);
        global_high_score = sharedPreferences.getInt("HIGH_SCORE",0);

        textView.setText(name + "'s high score: " +String.valueOf(high_score));
        highScoreView.setText("Global High Score: " +String.valueOf(global_high_score));



        textview2.setText(name);

        buttonPlayer1 = (Button) findViewById(R.id.buttonPlayer1);

        player1FirstCardImage = (ImageView) findViewById(R.id.player1FirstCard);
        player1SecondCardImage = (ImageView) findViewById(R.id.player1SecondCard);
        player1ThirdCardImage = (ImageView) findViewById(R.id.player1ThirdCard);
        player1FourthCardImage = (ImageView) findViewById(R.id.player1FourthCard);
        player1FifthCardImage = (ImageView) findViewById(R.id.player1FifthCard);
        player1SixthCardImage = (ImageView) findViewById(R.id.player1SixthCard);


        player2FirstCardImage = (ImageView) findViewById(R.id.player2FirstCard);
        player2SecondCardImage = (ImageView) findViewById(R.id.player2SecondCard);
        player2ThirdCardImage = (ImageView) findViewById(R.id.player2ThirdCard);
        player2FourthCardImage = (ImageView) findViewById(R.id.player2FourthCard);
        player2FifthCardImage = (ImageView) findViewById(R.id.player2FifthCard);
        player2SixthCardImage = (ImageView) findViewById(R.id.player2SixthCard);

        buttonGameResult = (Button) findViewById(R.id.buttonResult);
        textResult = (TextView) findViewById(R.id.gameResult);
        p1score = (TextView)findViewById(R.id.p1_score_text_view);
        p2score = (TextView)findViewById(R.id.p2_score_text_view);

        // buttonAnotherPlay = (Button) findViewById(R.id.buttonPlayAgain);

    }

    public void onPlayer1ButtonClick(View view) {
        int i = 0;
        while(i < 7){
            hand1Details = new ArrayList<String>();
            Player1AllIcons = new ArrayList<String>();

            ArrayList<ImageView> player1CardIconImageViews = new ArrayList<>();
            player1CardIconImageViews.add(player1FirstCardImage);
            player1CardIconImageViews.add(player1SecondCardImage);
            player1CardIconImageViews.add(player1ThirdCardImage);
            player1CardIconImageViews.add(player1FourthCardImage);
            player1CardIconImageViews.add(player1FifthCardImage);
            player1CardIconImageViews.add(player1SixthCardImage);

            int imageViewIndex = 0;

            if (player1Hand != null && player1Hand.size() == 6) return;

            player1Hand = game.dealPlayer1Card();
            player1DealtCardRank = game.getplayer1DealtCardRank();
            player1DealtCardSuit = game.getplayer1DealtCardSuit();


            for (Card card : player1Hand) {
                Suit suit = card.getSuit();
                Rank rank = card.getRank();
                int cardValue = card.getValue(rank);

                player1CardDetails = rank + " of " + suit;
                player1EachIcon = card.getCardIcon(player1CardDetails);

                setCardImage(player1EachIcon, player1CardIconImageViews.get(imageViewIndex));
                imageViewIndex++;

                Player1AllIcons.add(player1EachIcon);
                hand1Details.add(player1CardDetails);
            }

            hand2Details = new ArrayList<String>();
            Player2AllIcons = new ArrayList<String>();

            ArrayList<ImageView> player2CardIconImageViews = new ArrayList<>();
            player2CardIconImageViews.add(player2FirstCardImage);
            player2CardIconImageViews.add(player2SecondCardImage);
            player2CardIconImageViews.add(player2ThirdCardImage);
            player2CardIconImageViews.add(player2FourthCardImage);
            player2CardIconImageViews.add(player2FifthCardImage);
            player2CardIconImageViews.add(player2SixthCardImage);

            int imageViewIndex2 = 0;

            if (player2Hand != null && player2Hand.size() == 6) return;

            player2Hand = game.dealPlayer2Card();
            player2DealtCardRank = game.getplayer2DealtCardRank();
            player2DealtCardSuit = game.getplayer2DealtCardSuit();

            for (Card card : player2Hand) {
                Suit suit = card.getSuit();
                Rank rank = card.getRank();
                int cardValue = card.getValue(rank);

                player2CardDetails = rank + " of " + suit;
                player2EachIcon = card.getCardIcon(player2CardDetails);

                setCardImage(player2EachIcon, player2CardIconImageViews.get(imageViewIndex2));
                imageViewIndex2++;

                Player2AllIcons.add(player2EachIcon);
                hand2Details.add(player2CardDetails);
            }
            i+=1;
            if (i == 6) {
                Card p15thCard = player1Hand.get(4);
                Card p16thCard = player1Hand.get(5);
                int p1FifthCard = p15thCard.getValue(p15thCard.getRank());
                int p1SixthCard = p16thCard.getValue(p16thCard.getRank());
                Card p25thCard = player2Hand.get(4);
                Card p26thCard = player2Hand.get(5);
                int p2FifthCard = p25thCard.getValue(p25thCard.getRank());
                int p2SixthCard = p26thCard.getValue(p26thCard.getRank());
                System.out.println(p2FifthCard);
                System.out.println(p2SixthCard);
                player1HandNewValue = game.getPlayer1HandNewValue() - (p1FifthCard+p1SixthCard);
                System.out.println(player1HandNewValue);
                p1score.setText(String.valueOf(player1HandNewValue));
                player2HandNewValue = game.getPlayer2HandNewValue() - (p2FifthCard+p2SixthCard);
                System.out.println(player2HandNewValue);
                p2score.setText(String.valueOf(player2HandNewValue));
            }

        }

    }

    public void onResultButtonClick(View view) {
        TextView textView2 = (TextView)findViewById(R.id.p1_name_txt_view);
        Card p15thCard = player1Hand.get(4);
        Card p16thCard = player1Hand.get(5);
        int p1FifthCard = p15thCard.getValue(p15thCard.getRank());
        int p1SixthCard = p16thCard.getValue(p16thCard.getRank());
        Card p25thCard = player2Hand.get(4);
        Card p26thCard = player2Hand.get(5);
        int p2FifthCard = p25thCard.getValue(p25thCard.getRank());
        int p2SixthCard = p26thCard.getValue(p26thCard.getRank());
        player1HandNewValue = (game.getPlayer1HandNewValue() - (p1FifthCard+p1SixthCard));
        player2HandNewValue = (game.getPlayer2HandNewValue() - (p2FifthCard+p2SixthCard));
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("Second Chance!");
        builder.setMessage("Would you like to exchange 10 points for 2 additional cards?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView textView = (TextView)findViewById(R.id.p1_name_txt_view);
                String name = textView.getText().toString();
                int player1HandNewValue1 = player1HandNewValue - 5;
                int player2HandNewValue1 = player2HandNewValue + 5;
                p1score.setText(String.valueOf(player1HandNewValue1));
                p2score.setText(String.valueOf(player2HandNewValue1));
                String outcome = game.getResult(player1HandNewValue1, player2HandNewValue1);
                textResult.setText(outcome);
                high_score = sharedPreferences.getInt(name, 0);
                global_high_score = sharedPreferences.getInt("HIGH_SCORE",0);
                if(high_score<player1HandNewValue1) {
                    sharedPreferences.edit().putInt(name, player1HandNewValue1).commit();
                    if(global_high_score<player1HandNewValue1) {
                        sharedPreferences.edit().putInt("HIGH_SCORE", player1HandNewValue1).commit();
                    }
                }
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView textView = (TextView)findViewById(R.id.p1_name_txt_view);
                String name = textView.getText().toString();
                player1FifthCardImage.setVisibility(View.VISIBLE);
                player1SixthCardImage.setVisibility(View.VISIBLE);
                int player1HandNewValue2 = game.getPlayer1HandNewValue() - 10;
                p1score.setText(String.valueOf(player1HandNewValue2));

                if (player1HandNewValue2 >= player2HandNewValue){

                    player2FifthCardImage.setVisibility(View.VISIBLE);
                    player2SixthCardImage.setVisibility(View.VISIBLE);
                    int player2HandNewValue2 = game.getPlayer2HandNewValue() - 10;
                    p2score.setText(String.valueOf(player2HandNewValue2));
                    String outcome = game.getResult(player1HandNewValue2, player2HandNewValue2);
                    textResult.setText(outcome);
                    high_score = sharedPreferences.getInt(name, 0);
                    global_high_score = sharedPreferences.getInt("HIGH_SCORE",0);
                    if(high_score<player1HandNewValue2) {
                        sharedPreferences.edit().putInt(name, player1HandNewValue2).commit();
                        if (global_high_score < player1HandNewValue2) {
                            sharedPreferences.edit().putInt("HIGH_SCORE", player1HandNewValue2).commit();
                        }
                    }
                }else{
                    String outcome = game.getResult(player1HandNewValue2, player2HandNewValue);
                    textResult.setText(outcome);
                    high_score = sharedPreferences.getInt(name, 0);
                    global_high_score = sharedPreferences.getInt("HIGH_SCORE",0);
                    if(high_score<player1HandNewValue2){
                        sharedPreferences.edit().putInt(name, player1HandNewValue2).commit();
                        if(global_high_score<player1HandNewValue2){
                            sharedPreferences.edit().putInt("HIGH_SCORE",player1HandNewValue2).commit();
                        }
                    }
                }
            }
        });
        builder.show();

    }

    public void setCardImage(String card, ImageView imageView) {
        //card param example= "ace_of_spades"
        int imageId = getResources().getIdentifier(card, "drawable", getPackageName());
        imageView.setImageResource(imageId);
    }

}
