
package com.battlelancer.seriesguide.adapters;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

/**
 * Based on {@link FragmentPagerAdapter}, but removes fragments returning
 * {@link #POSITION_NONE} in addition to detaching them.
 */
abstract class MultiPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager mFragmentManager;

    private FragmentTransaction mCurTransaction;

    MultiPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (getItemPosition(object) == POSITION_NONE) {
            if (mCurTransaction == null) {
                // transaction is committed in #finishUpdate
                mCurTransaction = mFragmentManager.beginTransaction();
            }
            mCurTransaction.remove((Fragment) object);
        } else {
            super.destroyItem(container, position, object);
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);

        if (mCurTransaction != null) {
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }
}
