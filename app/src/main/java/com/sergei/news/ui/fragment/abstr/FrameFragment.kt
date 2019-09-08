package com.sergei.news.ui.fragment.abstr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sergei.news.extension.transaction
import kotlin.random.Random

abstract class FrameFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            if (it.id != -1) {
                it.id = Random(System.currentTimeMillis()).nextInt()
            }
        }
    }

    private var mAttachedFragment: Fragment? = null

    fun <T> attachFragment(fragment: T) where T : Fragment, T : Loggable {

        childFragmentManager.transaction {
            mAttachedFragment?.let { remove(it) }
            add(view!!.id, fragment, fragment.className)

        }

        mAttachedFragment = fragment
    }

    fun <T> detachFragment(fragment: Class<T>) where T : Fragment, T : Loggable {

        mAttachedFragment = null

        childFragmentManager.findFragmentByTag(fragment.canonicalName)?.let {
            childFragmentManager.transaction {
                remove(it)
            }
        }
    }
}

