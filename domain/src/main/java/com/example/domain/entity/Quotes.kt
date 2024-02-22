package com.example.domain.entity

data class Quotes(
    val acd: Double? = null,
    val baf: Double? = null,
    val bap: Double? = null,
    val bas: Double? = null,
    val baseContractCode: String? = null,
    val baseCurrency: String? = null,
    val baseLtr: String? = null,
    val bat: String? = null,
    val bbf: Double? = null,
    val bbp: Double? = null,
    val bbs: Double? = null,
    val bbt: String? = null,
    val c: String? = null,
    val chg: Double? = null,
    val chg110: Double? = null,
    val chg22: Double? = null,
    val chg220: Double? = null,
    val chg5: Double? = null,
    val ClosePrice: Double? = null,
    val codesubNm: String? = null,
    val cpn: Double? = null,
    val cpp: Double? = null,
    val delta: Double? = null,
    val dpb: Double? = null,
    val dps: Double? = null,
    val emitentType: String? = null,
    val fv: Double? = null,
    val gamma: Double? = null,
    val init: Double? = null,
    val ipo: String? = null,
    val issueNb: String? = null,
    val kind: Double? = null,
    val ltp: Double? = null,
    val ltr: String? = null,
    val lts: Double? = null,
    val ltt: String? = null,
    val marketStatus: String? = null,
    val maxtp: Double? = null,
    val minStep: Double? = null,
    val mintp: Double? = null,
    val mrg: String? = null,
    val mtd: String? = null,
    val n: Double? = null,
    val name: String? = null,
    val name2: String? = null,
    val ncd: String? = null,
    val ncp: Double? = null,
    val op: Double? = null,
    val optionType: String? = null,
    val otcInstr: String? = null,
    val p110: Double? = null,
    val p22: Double? = null,
    val p220: Double? = null,
    val p5: Double? = null,
    val pcp: Double? = null,
    val pp: Double? = null,
    val quoteBasis: String? = null,
    val rev: Double? = null,
    val schemeCalc: String? = null,
    val stepPrice: Double? = null,
    val strikePrice: Double? = null,
    val theta: Double? = null,
    val trades: Double? = null,
    val TradingReferencePrice: Double? = null,
    val TradingSessionSubID: String? = null,
    val type: Double? = null,
    val UTCOffset: Double? = null,
    val virtBaseInstr: String? = null,
    val vlt: Double? = null,
    val vol: Double? = null,
    val Volatility: Double? = null,
    val xAggFutures: String? = null,
    val xCurr: String? = null,
    val xCurrVal: Double? = null,
    val xDescr: String? = null,
    val xDsc1: Double? = null,
    val xDsc1Reception: String? = null,
    val xDsc2: Double? = null,
    val xDsc2Reception: String? = null,
    val xDsc3: Double? = null,
    val xIstrade: Double? = null,
    val xLot: Double? = null,
    val xMax: Double? = null,
    val xMin: Double? = null,
    val xMinLotQ: Double? = null,
    val xShort: Double? = null,
    val xShortReception: String? = null,
    val yld: Double? = null,
    val yldYtmAsk: Double? = null,
    val yldYtmBid: Double? = null,
    /////////////////////////////////
    val ltpDiff: Double = 0.0,
    val ltpDiffPercent: Double = 0.0,
    val increased: Boolean = false,
    val decreased: Boolean = false,
    val positive: Boolean = false,
    val logoUrl: String = ""
    /////////////////////////////////
)

fun Quotes.copyWith(new: Quotes) =
    Quotes(
        acd = new.acd ?: this.acd,
        baf = new.baf ?: this.baf,
        bap = new.bap ?: this.bap,
        bas = new.bas ?: this.bas,
        baseContractCode = new.baseContractCode ?: this.baseContractCode,
        baseCurrency = new.baseCurrency ?: this.baseCurrency,
        baseLtr = new.baseLtr ?: this.baseLtr,
        bat = new.bat ?: this.bat,
        bbf = new.bbf ?: this.bbf,
        bbp = new.bbp ?: this.bbp,
        bbs = new.bbs ?: this.bbs,
        bbt = new.bbt ?: this.bbt,
        c = new.c ?: this.c,
        chg = new.chg ?: this.chg,
        chg110 = new.chg110 ?: this.chg110,
        chg22 = new.chg22 ?: this.chg22,
        chg220 = new.chg220 ?: this.chg220,
        chg5 = new.chg5 ?: this.chg5,
        ClosePrice = new.ClosePrice ?: this.ClosePrice,
        codesubNm = new.codesubNm ?: this.codesubNm,
        cpn = new.cpn ?: this.cpn,
        cpp = new.cpp ?: this.cpp,
        delta = new.delta ?: this.delta,
        dpb = new.dpb ?: this.dpb,
        dps = new.dps ?: this.dps,
        emitentType = new.emitentType ?: this.emitentType,
        fv = new.fv ?: this.fv,
        gamma = new.gamma ?: this.gamma,
        init = new.init ?: this.init,
        ipo = new.ipo ?: this.ipo,
        issueNb = new.issueNb ?: this.issueNb,
        kind = new.kind ?: this.kind,
        ltp = new.ltp ?: this.ltp,
        ltr = new.ltr ?: this.ltr,
        lts = new.lts ?: this.lts,
        ltt = new.ltt ?: this.ltt,
        marketStatus = new.marketStatus ?: this.marketStatus,
        maxtp = new.maxtp ?: this.maxtp,
        minStep = new.minStep ?: this.minStep,
        mintp = new.mintp ?: this.mintp,
        mrg = new.mrg ?: this.mrg,
        mtd = new.mtd ?: this.mtd,
        n = new.n ?: this.n,
        name = new.name ?: this.name,
        name2 = new.name2 ?: this.name2,
        ncd = new.ncd ?: this.ncd,
        ncp = new.ncp ?: this.ncp,
        op = new.op ?: this.op,
        optionType = new.optionType ?: this.optionType,
        otcInstr = new.otcInstr ?: this.otcInstr,
        p110 = new.p110 ?: this.p110,
        p22 = new.p22 ?: this.p22,
        p220 = new.p220 ?: this.p220,
        p5 = new.p5 ?: this.p5,
        pcp = new.pcp ?: this.pcp,
        pp = new.pp ?: this.pp,
        quoteBasis = new.quoteBasis ?: this.quoteBasis,
        rev = new.rev ?: this.rev,
        schemeCalc = new.schemeCalc ?: this.schemeCalc,
        stepPrice = new.stepPrice ?: this.stepPrice,
        strikePrice = new.strikePrice ?: this.strikePrice,
        theta = new.theta ?: this.theta,
        trades = new.trades ?: this.trades,
        TradingReferencePrice = new.TradingReferencePrice ?: this.TradingReferencePrice,
        TradingSessionSubID = new.TradingSessionSubID ?: this.TradingSessionSubID,
        type = new.type ?: this.type,
        UTCOffset = new.UTCOffset ?: this.UTCOffset,
        virtBaseInstr = new.virtBaseInstr ?: this.virtBaseInstr,
        vlt = new.vlt ?: this.vlt,
        vol = new.vol ?: this.vol,
        Volatility = new.Volatility ?: this.Volatility,
        xAggFutures = new.xAggFutures ?: this.xAggFutures,
        xCurr = new.xCurr ?: this.xCurr,
        xCurrVal = new.xCurrVal ?: this.xCurrVal,
        xDescr = new.xDescr ?: this.xDescr,
        xDsc1 = new.xDsc1 ?: this.xDsc1,
        xDsc1Reception = new.xDsc1Reception ?: this.xDsc1Reception,
        xDsc2 = new.xDsc2 ?: this.xDsc2,
        xDsc2Reception = new.xDsc2Reception ?: this.xDsc2Reception,
        xDsc3 = new.xDsc3 ?: this.xDsc3,
        xIstrade = new.xIstrade ?: this.xIstrade,
        xLot = new.xLot ?: this.xLot,
        xMax = new.xMax ?: this.xMax,
        xMin = new.xMin ?: this.xMin,
        xMinLotQ = new.xMinLotQ ?: this.xMinLotQ,
        xShort = new.xShort ?: this.xShort,
        xShortReception = new.xShortReception ?: this.xShortReception,
        yld = new.yld ?: this.yld,
        yldYtmAsk = new.yldYtmAsk ?: this.yldYtmAsk,
        yldYtmBid = new.yldYtmBid ?: this.yldYtmBid,
        /////////////calculated fields///////////
        ltpDiff = calcDiff(this.ltpDiff, new.ltp, this.ltp),
        ltpDiffPercent = calcDiffPercent(this.ltpDiffPercent, this.ltp, new.ltp),
        increased = calcDiffPercent(this.ltpDiffPercent, this.ltp, new.ltp) > this.ltpDiffPercent,
        decreased = calcDiffPercent(this.ltpDiffPercent, this.ltp, new.ltp) < this.ltpDiffPercent,
        positive = calcDiffPercent(this.ltpDiffPercent, this.ltp, new.ltp) > 0,
        logoUrl = this.logoUrl
        /////////////////////////////////////////
    )

fun calcDiff(oldDiff: Double, new: Double?, old: Double?): Double {
    old?: return oldDiff
    new?: return oldDiff
    val newDiff = new - old

    return newDiff
}

fun calcDiffPercent(oldPercent: Double, old: Double?, new: Double?): Double {
    old?: return oldPercent
    new?: return oldPercent
    val newDiff = new - old
    val newPercent = (newDiff / old) * 100

    return newPercent
}