package com.imagesearcher.controllers.callbacks;

import com.imagesearcher.database.pojo.Items;

/**
 * @author equa1s.
 */

public interface GoogleSearchCallback {
    void onSuccess(Items items);
    void onFinish();
    void onFailure(Throwable throwable);
}
