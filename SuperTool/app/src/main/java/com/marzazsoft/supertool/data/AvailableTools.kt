package com.marzazsoft.supertool.data

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.models.Tool
import com.marzazsoft.supertooldesign.utils.blue
import com.marzazsoft.supertooldesign.utils.green
import com.marzazsoft.supertooldesign.utils.superLightBlue
import com.marzazsoft.supertooldesign.utils.yellow
import com.marzazsoft.supertooldesign.R as DesignR

fun getLoggedUserTools(context: Context): List<Tool> =
    with(context) {
        listOf<Tool>(
            Tool(
                id = 0,
                color = yellow.toArgb(),
                icon = R.drawable.ic_games,
                title = getString(R.string.online_games_title),
                resume = getString(R.string.online_games_resume),
            ),
            Tool(
                id = 1,
                color = superLightBlue.toArgb(),
                icon = R.drawable.ic_radio,
                title = getString(R.string.radio_title),
                resume = getString(R.string.radio_resume),
            ),
            Tool(
                id = 2,
                color = green.toArgb(),
                icon = R.drawable.ic_notes,
                title = getString(R.string.notes_title),
                resume = getString(R.string.notes_resume),
            ),
            Tool(
                id = 3,
                color = blue.toArgb(),
                icon = DesignR.drawable.ic_money,
                title = getString(R.string.spend_manager_title),
                resume = getString(R.string.spend_manager_resume),
            ),
        )
    }

fun getGuestUserTools(context: Context): List<Tool> =
    with(context) {
        listOf<Tool>(
            Tool(
                id = 2,
                color = green.toArgb(),
                icon = R.drawable.ic_notes,
                title = getString(R.string.notes_title),
                resume = getString(R.string.notes_resume),
            ),
        )
    }
