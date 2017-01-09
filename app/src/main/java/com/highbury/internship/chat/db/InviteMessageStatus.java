package com.highbury.internship.chat.db;

/**
 * Created by han on 2017/1/5.
 */

public enum InviteMessageStatus {
    //==contact
    /**being invited*/
    BEINVITEED,
    /**being refused*/
    BEREFUSED,
    /**remote user already agreed*/
    BEAGREED,

    //==group application
    /**remote user apply to join*/
    BEAPPLYED,
    /**you have agreed to join*/
    AGREED,
    /**you refused the join request*/
    REFUSED,

    //==group invitation
    /**received remote user's invitation**/
    GROUPINVITATION,
    /**remote user accept your invitation**/
    GROUPINVITATION_ACCEPTED,
    /**remote user declined your invitation**/
    GROUPINVITATION_DECLINED
}
