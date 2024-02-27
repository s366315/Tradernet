package com.example.tradernet

enum class  Requests(private val event: String) {
    REALTIME_QUOTES("realtimeQuotes");

    operator fun invoke() = event
}

enum class Tickers(private val symbol: String) {
    SP500_IDX("SP500.IDX"),
    AAPL_US("AAPL.US"),
    RSTI("RSTI"),
    GAZP("GAZP"),
    MRKZ("MRKZ"),
    RUAL("RUAL"),
    HYDR("HYDR"),
    MRKS("MRKS"),
    SBER("SBER"),
    FEES("FEES"),
    TGKA("TGKA"),
    VTBR("VTBR"),
    ANH_US("ANH.US"),
    VICL_US("VICL.US"),
    BURG_US("BURG.US"),
    NBL_US("NBL.US"),
    YETI_US("YETI.US"),
    WSFS_US("WSFS.US"),
    NIO_US("NIO.US"),
    DXC_US("DXC.US"),
    MIC_US("MIC.US"),
    HSBC_US("HSBC.US"),
    EXPN_EU("EXPN.EU"),
    GSK_EU("GSK.EU"),
    SHP_EU("SHP.EU"),
    MAN_EU("MAN.EU"),
    DB1_EU("DB1.EU"),
    MUV2_EU("MUV2.EU"),
    TATE_EU("TATE.EU"),
    KGF_EU("KGF.EU"),
    MGGT_EU("MGGT.EU"),
    SGGD_EU("SGGD.EU"),
    DAX_IDX("DAX.IDX"),
    NASDAQ_IDX("NASDAQ.IDX"),
    FTSE_IDX("FTSE.IDX");

    operator fun invoke() = symbol

    companion object {
        fun getAll() = entries.map { it() }
    }
}