package com.marzazsoft.supertool.data

import android.content.Context
import com.marzazsoft.supertool.R
import com.marzazsoft.supertool.models.Tool
import com.marzazsoft.supertool.presentation.ui.theme.green
import com.marzazsoft.supertool.presentation.ui.theme.superLightBlue
import com.marzazsoft.supertool.presentation.ui.theme.yellow

fun getLoggedUserTools(context: Context): List<Tool> =
    with(context) {
        listOf<Tool>(
            Tool(
                id = 0,
                color = yellow,
                icon = R.drawable.ic_games,
                title = getString(R.string.online_games_title),
                resume = getString(R.string.online_games_resume),
            ),
            Tool(
                id = 1,
                color = superLightBlue,
                icon = R.drawable.ic_radio,
                title = getString(R.string.radio_title),
                resume = getString(R.string.radio_resume),
            ),
            Tool(
                id = 2,
                color = green,
                icon = R.drawable.ic_notes,
                title = getString(R.string.notes_title),
                resume = getString(R.string.notes_resume),
            ),
        )
    }

fun getGuestUserTools(context: Context): List<Tool> =
    with(context) {
        listOf<Tool>(
            Tool(
                id = 0,
                color = yellow,
                icon = R.drawable.ic_games,
                title = getString(R.string.online_games_title),
                resume = getString(R.string.online_games_resume),
            ),
            Tool(
                id = 1,
                color = superLightBlue,
                icon = R.drawable.ic_radio,
                title = getString(R.string.radio_title),
                resume = getString(R.string.radio_resume),
            ),
        )
    }
