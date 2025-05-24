package com.marzazsoft.mobile.radio.data

import com.marzazsoft.mobile.radio.models.Station

fun getGamesList(): List<Station> =
    listOf(
        Station(
            name = "La Mejor 95.5 fm",
            stationIcon = "https://lamejor.com.mx/u/plantillas/p/la-mejor-fm/imgs/main-logo.svg?vok",
            stationUri = "https://19003.live.streamtheworld.com/XERCFM.mp3?dist=tunein&DIST=TuneIn&TGT=TuneIn&maxServers=2&gdpr=0&partnertok=eyJhbGciOiJIUzI1NiIsImtpZCI6InR1bmVpbiIsInR5cCI6IkpXVCJ9.eyJ0cnVzdGVkX3BhcnRuZXIiOnRydWUsImlhdCI6MTc0ODA0NDc4MSwiaXNzIjoidGlzcnYifQ.w7th5PbUwv03GryFqtBVOeYcP2wiklWY-yeakhd5G2s",
        ),
        Station(
            name = "La Z 107.3 fm",
            stationIcon = "https://editorial.laz.mx/wp-content/uploads/2023/04/Logo_LaZ_23.svg",
            stationUri = "https://19313.live.streamtheworld.com/XEQR_FMAAC.aac?dist=grc-web&key=grc-web&tdsdk=js-2.9&swm=false&pname=TDSdk&pversion=2.9&banners=none&burst-time=15&sbmid=b2e5e9f8-ceb2-4923-8c52-30025333b1e5",
        ),
    )
