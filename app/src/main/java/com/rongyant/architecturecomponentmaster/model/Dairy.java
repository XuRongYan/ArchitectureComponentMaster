package com.rongyant.architecturecomponentmaster.model;

import java.util.Date;

/**
 * Created by XRY on 2017/7/19.
 */

public interface Dairy {
    int getUserId();
    int getDiaryId();
    String getDairyContent();
    Date getDate();
}
