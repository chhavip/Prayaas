package com.example.chhavi.prayaas;

import android.provider.BaseColumns;

/**
 * Created by shikharkhetan on 6/2/15.
 */
public class PrayaasContract implements BaseColumns {

    private PrayaasContract(){}

    public static final String DB_NAME = "Prayaas";
    public static final String USER_TABLE="User";
    public static final String USER_TABLE_USERNAME_COL="Username";
    public static final String USER_TABLE_PASSWORD_COL="Password";
    public static final String USER_TABLE_NAME_COL="Name";
    public static final String USER_TABLE_AGE_COL="Age";
    public static final String USER_TABLE_PHONE_COL="Phone";
    public static final String USER_TABLE_GENDER_COL="Gender";
    public static final String USER_TABLE_REFERRAL_COL="Referral";
    public static final String USER_TABLE_WALLET="UserWallet";
    public static final String USER_TABLE_DONATED="UserDonated";

    public static final String EVENT_TABLE="events";
    public static final String EVENT_TABLE_ID="EventId";
    public static final String EVENT_TABLE_NAME="eventName";
    public static final String EVENT_TABLE_DESCRIPTION="eventDescription";
    public static final String EVENT_TABLE_TIME="time";
    public static final String EVENT_TABLE_VENUE="venue";
    public static final String EVENT_TABLE_DATE="date";
    public static final String EVENT_TABLE_CONTACT="eventContact";
    public static final String EVENT_TABLE_DURATION="eventDuration";
    public static final String EVENT_TABLE_STATUS="eventStatus";
    public static final String EVENT_TABLE_ORGANISATION="eventOrganisation";
    public static final String EVENT_TABLE_SEATS="eventSeats";

    public static final String REWARDS_TABLE="Rewards";
    public static final String REWARDS_TABLE_WALLET_ID="RewardsWalletId";
    public static final String REWARDS_TABLE_MONEY="RewardMoney";








}
