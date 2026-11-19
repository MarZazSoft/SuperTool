package com.marzazsoft.mobile.radio.data

import com.marzazsoft.mobile.radio.models.RadioType
import com.marzazsoft.mobile.radio.models.Station

@Suppress("ktlint:standard:max-line-length")
fun getRadioStationsList(): List<Station> =
    listOf(
        Station(
            name = "La Mejor 95.5 fm",
            stationType = RadioType.FM,
            stationIcon = "https://i.iheart.com/v3/re/new_assets/5ee43a62c4c516799194024d?ops=fit(960%2C960)%2Cresize(0%2C390)",
            stationUri = "https://playerservices.streamtheworld.com/api/livestream-redirect/XHRO_SC",
        ),
        Station(
            name = "La Z 107.3 fm",
            stationType = RadioType.FM,
            stationIcon = "https://imgsvr.radiocut.site/get/crop/center/200/200/radio_logos/96/b2/96b243f3-b93b-4964-823f-d23f5e1a28ea.jpg",
            stationUri = "https://playerservices.streamtheworld.com/api/livestream-redirect/XEQR_FMAAC.aac",
        ),
        Station(
            name = "MyTalk 107.1",
            stationType = RadioType.FM,
            stationIcon = "https://www.mytalk1071.com/wp-content/uploads/2020/06/mt_bubble.jpg",
            stationUri = "https://playerservices.streamtheworld.com/api/livestream-redirect/KTMYFM.mp3",
        ),
    )
