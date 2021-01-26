package com.ts.can;

import android.util.Log;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.can.audi.lz.CanAudiLzWithCDCarFuncView;
import com.ts.can.audi.lz.CanAudiLzWithCDCarInfoView;
import com.ts.can.audi.lz.CanAudiLzWithCDCarInitView;
import com.ts.can.audi.lz.CanAudiLzWithCDUpdateView;
import com.ts.can.audi.xbs.CanAudiXbsWithCDCarFuncView;
import com.ts.can.audi.xbs.CanAudiXbsWithCDCarInfoView;
import com.ts.can.audi.xbs.CanAudiXbsWithCDCarInitView;
import com.ts.can.audi.xbs.CanAudiXbsWithCDUpdateView;
import com.ts.can.audi.xhd.CanAudiWithCDCarFuncView;
import com.ts.can.audi.xhd.CanAudiWithCDCarInfoView;
import com.ts.can.audi.xhd.CanAudiWithCDCarInitView;
import com.ts.can.baic.ec180.CanBaicEc5CarSetView;
import com.ts.can.baic.senova.CanSenovaCarAssistSetView;
import com.ts.can.baic.wc.hss6.CanBaicHSS6WcAirSetView;
import com.ts.can.baic.wc.hss6.CanBaicHSS6WcCameraSetView;
import com.ts.can.baic.wc.hss6.CanBaicHSS6WcCarInfoSetView;
import com.ts.can.baic.wc.hss6.CanBaicHSS6WcYiBiaoSetView;
import com.ts.can.benc.wc.Metris.CanBencMetrisWcCarSetView;
import com.ts.can.benc.withcd.CanBencWithCDAutoBreakView;
import com.ts.can.benc.withcd.CanBencWithCDCarSetView;
import com.ts.can.bmw.lz.CanBMWLzAmpSetView;
import com.ts.can.bmw.lz.CanBMWLzCarSetView;
import com.ts.can.bmw.lz.CanBMWLzConsumptionView;
import com.ts.can.bmw.lz.CanBMWLzYbxxView;
import com.ts.can.bmw.lz.bmw2.CanBMW2LzCarTpmsView;
import com.ts.can.bmw.mini.CanBMWMiniUpdateView;
import com.ts.can.bmw.x1.wc.CanBMWX1WcDriveComputeView;
import com.ts.can.bmw.x1.wc.CanBMWX1WcZCTFView;
import com.ts.can.bmw.zmyt.CanBmwZmytWithCDCarFuncView;
import com.ts.can.bmw.zmyt.CanBmwZmytWithCDCarInfoView;
import com.ts.can.bmw.zmyt.CanBmwZmytWithCDCarInitView;
import com.ts.can.byd.dj.m6.CanBydM6DjCarSetView;
import com.ts.can.byd.hcy.s6s7.CanBydS6S7AmpSetView;
import com.ts.can.byd.hcy.s6s7.CanBydS6S7CarWinSetView;
import com.ts.can.byd.hcy.s6s7.CanBydS6S7PM25SetView;
import com.ts.can.byd.rsw.CanBydRswCarSetView;
import com.ts.can.byd.rsw.CanBydRswPm25SetView;
import com.ts.can.cc.dj.hf.CanHfDjAmpSetView;
import com.ts.can.cc.dj.hf.CanHfDjCarPdInfoView;
import com.ts.can.cc.dj.hf.CanHfDjCarSetView;
import com.ts.can.cc.dj.hf.CanHfDjCarStatusView;
import com.ts.can.cc.dj.hf.CanHfDjSeatStatusView;
import com.ts.can.cc.h6_rzc.CanCCH6RzcCarSeatSetView;
import com.ts.can.cc.h6_rzc.CanCCH6RzcCarSeatStatusView;
import com.ts.can.cc.h6_rzc.CanCcRzcAvmSetView;
import com.ts.can.cc.h6_rzc.CanCcRzcDrivAssistSetView;
import com.ts.can.cc.wc.CanCCWcAmpSetView;
import com.ts.can.cc.wc.CanCCWcCarSetView;
import com.ts.can.cc.wc.CanCCWcCarTypeView;
import com.ts.can.cc.wc.CanCCWcLangSetView;
import com.ts.can.cc.wc.h2.CanCCH2WcCameraSetView;
import com.ts.can.cc.wc.h6.CanWcH6AVMSetView;
import com.ts.can.cc.wc.h6.CanWcH6AmpSetView;
import com.ts.can.cc.wc.h6.CanWcH6CarSetView;
import com.ts.can.cc.wc.h6.CanWcH6LangSetView;
import com.ts.can.cc.wc.h6.CanWcH6LightSetView;
import com.ts.can.chana.cs75.CanCs75CameraSetView;
import com.ts.can.chana.od.CanChanaODAirSetView;
import com.ts.can.chana.od.CanChanaODCSXXView;
import com.ts.can.chana.od.CanChanaODCarSetView;
import com.ts.can.chana.wc.CanCs75WcCarSetActivity;
import com.ts.can.chana.wc.CanCs75WcVehicleInfoView;
import com.ts.can.chana.wc.cos.CanChanACosCarSetView;
import com.ts.can.chana.wc.cos.CanChanACosDvrSetView;
import com.ts.can.chana.wc.cos.CanChanACosTPMSView;
import com.ts.can.chana.wc.os.CanChanaWcCarSetView;
import com.ts.can.chery.wc.CanCheryWcCarDrivAssistSetView;
import com.ts.can.chrysler.CanChrOthAmpSetView;
import com.ts.can.chrysler.CanChrOthSetSuspensionView;
import com.ts.can.chrysler.rz.CanRZygPanControlView;
import com.ts.can.chrysler.rz.CanRZygSetSuspensionView;
import com.ts.can.chrysler.rz.CanRZygSetWiperMirrorView;
import com.ts.can.chrysler.rz.CanRZygSetXnkzxtView;
import com.ts.can.chrysler.txb.CanChryslerTxbAmpSetView;
import com.ts.can.chrysler.wc.CanChryslerWcACSetView;
import com.ts.can.chrysler.wc.CanChryslerWcAmpSetView;
import com.ts.can.chrysler.wc.CanChryslerWcCDView;
import com.ts.can.chrysler.wc.CanChryslerWcCarDrivAssistSetView;
import com.ts.can.chrysler.wc.CanChryslerWcCarLockSetView;
import com.ts.can.chrysler.wc.CanChryslerWcLightLockSetView;
import com.ts.can.chrysler.wc.CanChryslerWcSystemSetView;
import com.ts.can.chrysler.wc.compass.CanCompassWcAmpSetView;
import com.ts.can.chrysler.wc.compass.CanCompassWcLightSetView;
import com.ts.can.chrysler.wc.compass.CanCompassWcLockSetView;
import com.ts.can.chrysler.wc.compass.CanCompassWcSafeAssSetView;
import com.ts.can.chrysler.wc.compass.CanCompassWcUnitSetView;
import com.ts.can.chrysler.wc.jeep.CanJeepWcAmpSetView;
import com.ts.can.df.ax7.CanDFCarSetView;
import com.ts.can.df.fg_rzc.CanDFFGRzcCarSetView;
import com.ts.can.df.fg_rzc.CanDFFGRzcCsxxSetView;
import com.ts.can.df.fg_rzc.CanDFFGRzcDcxxSetView;
import com.ts.can.df.fg_rzc.CanDFFGRzcDjxxSetView;
import com.ts.can.df.od.ax7.CanDfAx7OdCarAvmSetView;
import com.ts.can.df.wc.ax7.CanDfFsWcCarSetView;
import com.ts.can.df.wc.ax7.CanDfFsWcDriveInfoView;
import com.ts.can.df.wc.jy.CanJYX5WcLightSetView;
import com.ts.can.df.wc.jy.CanJYX5WcLockSetView;
import com.ts.can.faw.dj.b70.CanB70DjCDView;
import com.ts.can.faw.dj.b70.CanB70DjRadioView;
import com.ts.can.faw.dj.b70.CanB70DjSetView;
import com.ts.can.faw.t3.b30.CanFawB30T3BatteryStatusView;
import com.ts.can.faw.t3.b30.CanFawB30T3CarSetView;
import com.ts.can.faw.t3.b30.CanFawB30T3MachineStatusView;
import com.ts.can.faw.t3.b30.CanFawB30T3UseInfoView;
import com.ts.can.fiat.rzc.CanFiatRzcCarSetView;
import com.ts.can.fiat.rzc.CanFiatRzcDriveInfoView;
import com.ts.can.fiat.rzc.CanFiatRzcLangSetInfoView;
import com.ts.can.fiat.rzc.CanFiatRzcLightSetView;
import com.ts.can.fiat.rzc.CanFiatRzcLockSetView;
import com.ts.can.fiat.rzc.CanFiatRzcParkAssistSetView;
import com.ts.can.fiat.rzc.CanFiatRzcUnitSetView;
import com.ts.can.fiat.wc.CanFiatWcCarSetView;
import com.ts.can.fiat.wc.CanFiatWcDriveInfoView;
import com.ts.can.fiat.wc.CanFiatWcLangSetInfoView;
import com.ts.can.fiat.wc.CanFiatWcUnitSetView;
import com.ts.can.ford.dj.CanFordDjCarSetView;
import com.ts.can.ford.f150.CanFordF150CarSetView;
import com.ts.can.ford.rzc.CanFordRzcAmpSetView;
import com.ts.can.ford.rzc.CanFordRzcCarSetView;
import com.ts.can.ford.rzc.CanFordRzcChairSetView;
import com.ts.can.ford.rzc.CanFordRzcChargeMixView;
import com.ts.can.ford.rzc.CanFordRzcDriveAssistSetView;
import com.ts.can.ford.rzc.CanFordRzcLockSetView;
import com.ts.can.ford.rzc.CanFordRzcOtherSetView;
import com.ts.can.ford.rzc.CanFordRzcSetLightView;
import com.ts.can.ford.rzc.CanFordRzcTpmsView;
import com.ts.can.ford.rzc.CanFordRzcZnxfSetView;
import com.ts.can.ford.wc.CanFordWcActiveParkView;
import com.ts.can.ford.wc.CanFordWcAmpSetView;
import com.ts.can.ford.wc.CanFordWcCarSetView;
import com.ts.can.ford.wc.CanFordWcLangSetView;
import com.ts.can.ford.wc.CanFordWcWarnInfoView;
import com.ts.can.ford.wc.fiesta.CanFordFiestaWcCarSetView;
import com.ts.can.ford.wc.fiesta.CanFordFiestaWcWarnInfoView;
import com.ts.can.ford.wc.mondeo.CanFordMondeoWcCarSetView;
import com.ts.can.ford.wc.mondeo.CanFordMondeoWcDrivAssistSetView;
import com.ts.can.ford.wc.mondeo.CanFordMondeoWcSystemSetView;
import com.ts.can.gac.trumpchi.CanGqcqNLXXSetView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcACSetView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcAttachSetView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcChairSetView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcChargSetView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcDrvAssSetView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcLangSetView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcLightSetView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcLinkSosView;
import com.ts.can.gac.trumpchi_wc.CanTrumpchiWcNLSetView;
import com.ts.can.geely.comm.CanGeelyRzcMixInfoView;
import com.ts.can.geely.comm.CanGeelyX3TpmsView;
import com.ts.can.geely.rzc.CanGeelyRzcCarAvmView;
import com.ts.can.geely.rzc.CanGeelyRzcCarInfoView;
import com.ts.can.geely.rzc.CanGeelyRzcPM25View;
import com.ts.can.gm.comm.CanGMHybridView;
import com.ts.can.gm.od.captiva.CanGmCaptivaOdPcInfoView;
import com.ts.can.gm.onstar.CanOnStarWifiView;
import com.ts.can.gm.rzc.CanGL8RearACView;
import com.ts.can.gm.rzc.CanGMActivityParkView;
import com.ts.can.gm.rzc.CanGMCarTypeView;
import com.ts.can.gm.rzc.CanGMOpelKeyView;
import com.ts.can.gm.rzc.CanGMRzcCarInfoView;
import com.ts.can.gm.rzc.CanGMSetACView;
import com.ts.can.gm.rzc.CanGMSetCDSView;
import com.ts.can.gm.rzc.CanGMSetConvView;
import com.ts.can.gm.rzc.CanGMSetLanguageView;
import com.ts.can.gm.rzc.CanGMSetLightView;
import com.ts.can.gm.rzc.CanGMSetLockView;
import com.ts.can.gm.rzc.CanGMSetOtherView;
import com.ts.can.gm.wc.CanGMWcCarStatusView;
import com.ts.can.gm.wc.CanGMWcCarTypeView;
import com.ts.can.gm.wc.CanGMWcOnStarSpkView;
import com.ts.can.gm.wc.CanGMWcSetACView;
import com.ts.can.gm.wc.CanGMWcSetCDSView;
import com.ts.can.gm.wc.CanGMWcSetConvView;
import com.ts.can.gm.wc.CanGMWcSetLanguageView;
import com.ts.can.gm.wc.CanGMWcSetLightView;
import com.ts.can.gm.wc.CanGMWcSetLockView;
import com.ts.can.gm.wc.CanGMWcSetOtherView;
import com.ts.can.gm.wc.CanGMWcTPMSView;
import com.ts.can.gm.wc.CanGMWcVehicleInfoView;
import com.ts.can.gm.wc.enclave.CanGmEnclaveWcSetACView;
import com.ts.can.gm.wc.enclave.CanGmEnclaveWcSetLanguageView;
import com.ts.can.hant.rzc.CanHantElectCarBaseInfoSetView;
import com.ts.can.hant.rzc.CanHantElectCarDriveSetView;
import com.ts.can.hant.rzc.CanHantElectCarKeyLockSetView;
import com.ts.can.hant.rzc.CanHantElectCarWarnInfoView;
import com.ts.can.honda.CanHonda360PanoramaSetView;
import com.ts.can.honda.CanHondaQJYXSystemSetView;
import com.ts.can.honda.CanHondaYiBiaoSetView;
import com.ts.can.honda.bnr.CanHondaBnrCarDrivAssistSetView;
import com.ts.can.honda.rzc.CanHondaDaRzcAmpSetView;
import com.ts.can.honda.rzc.CanHondaDaRzcCameraSetView;
import com.ts.can.honda.rzc.CanHondaDaRzcCarCsSetView;
import com.ts.can.honda.rzc.CanHondaDaRzcCarDistanceView;
import com.ts.can.honda.rzc.CanHondaDaRzcCarDrivAssistSetView;
import com.ts.can.honda.rzc.CanHondaDaRzcCarSetView;
import com.ts.can.honda.rzc.CanHondaDaRzcCarSystemSetView;
import com.ts.can.honda.rzc.CanHondaDaRzcCompassView;
import com.ts.can.honda.rzc.CanHondaDaRzcConsumpCurrentView;
import com.ts.can.honda.rzc.CanHondaDaRzcConsumpHistoryView;
import com.ts.can.honda.rzc.CanHondaDaRzcDistanceIllView;
import com.ts.can.honda.rzc.CanHondaDaRzcGllxxView;
import com.ts.can.honda.rzc.CanHondaDaRzcMotoRearDoorView;
import com.ts.can.honda.rzc.CanHondaDaRzcRemoteLockSetView;
import com.ts.can.honda.wc.CanHondaWcAmpSetView;
import com.ts.can.honda.wc.CanHondaWcCameraSetView;
import com.ts.can.honda.wc.CanHondaWcCarDistanceSetView;
import com.ts.can.honda.wc.CanHondaWcCarDrivAssistSetView;
import com.ts.can.honda.wc.CanHondaWcCarLockSetView;
import com.ts.can.honda.wc.CanHondaWcConsumpCurView;
import com.ts.can.honda.wc.CanHondaWcConsumpHisView;
import com.ts.can.honda.wc.CanHondaWcLightLockSetView;
import com.ts.can.honda.wc.CanHondaWcMotoRearDoorSetView;
import com.ts.can.honda.wc.CanHondaWcRemoteLockSetSetView;
import com.ts.can.honda.wc.CanHondaWcSystemSetView;
import com.ts.can.honda.wc.accord9.CanAccord9WcCameraSetView;
import com.ts.can.honda.wc.accord9.CanAccord9WcCarDrivAssistSetView;
import com.ts.can.honda.wc.accord9.CanAccord9WcCarLockSetView;
import com.ts.can.honda.wc.accord9.CanAccord9WcConsumpCurView;
import com.ts.can.honda.wc.accord9.CanAccord9WcConsumpHisView;
import com.ts.can.honda.wc.accord9.CanAccord9WcDistanceSetView;
import com.ts.can.honda.wc.accord9.CanAccord9WcLightLockSetView;
import com.ts.can.honda.wc.accord9.CanAccord9WcSystemSetView;
import com.ts.can.honda.wc.crown.CanCrownWcAmpSetView;
import com.ts.can.ht.x7.CanHtX5LightSetView;
import com.ts.can.hyundai.CanHyundaiAmpSetView;
import com.ts.can.hyundai.CanHyundaiBocheNaviSetView;
import com.ts.can.hyundai.CanHyundaiCarSetView;
import com.ts.can.hyundai.rzc.CanHyunDaiRzcNllctView;
import com.ts.can.hyundai.rzc.CanHyundaiRzcAirSetView;
import com.ts.can.hyundai.rzc.CanHyundaiRzcCdszSetView;
import com.ts.can.hyundai.rzc.CanHyundaiRzcEcoSetView;
import com.ts.can.hyundai.rzc.CanHyundaiRzcJsmsSetView;
import com.ts.can.hyundai.rzc.CanHyundaiRzcLightSetView;
import com.ts.can.hyundai.rzc.CanHyundaiRzcNyxxView;
import com.ts.can.hyundai.rzc.CanHyundaiRzcParkingSetView;
import com.ts.can.hyundai.rzc.CanHyundaiRzcYyszSetView;
import com.ts.can.hyundai.wc.CanHyundaiWc360CameraSetView;
import com.ts.can.hyundai.wc.CanHyundaiWcCarSetView;
import com.ts.can.hyundai.wc.CanHyundaiWcSetAmpView;
import com.ts.can.jac.CanJACCarInfoView;
import com.ts.can.jac.CanJACCarSetView;
import com.ts.can.jac.CanJACChargSetView;
import com.ts.can.jac.od.CanJACRefineOdCarSetView;
import com.ts.can.jac.od.CanJACRefineOdTpmsView;
import com.ts.can.jac.wc.CanJACRefineWcCDZTSetView;
import com.ts.can.jac.wc.CanJACRefineWcCarSetView;
import com.ts.can.jac.wc.CanJACRefineWcDrivAssistSetView;
import com.ts.can.jac.wc.CanJACRefineWcNLXXSetView;
import com.ts.can.jiangling.myx.CanJiangLingMyxCarSetView;
import com.ts.can.kawei.wc.CanKaWeiWcTpmsView;
import com.ts.can.landrover.zmyt.CanLandRoverZmytCarFuncView;
import com.ts.can.landrover.zmyt.CanLandRoverZmytCarInitView;
import com.ts.can.landrover.zmyt.CanLandRoverZmytUpdateView;
import com.ts.can.landwind.od.CanLandWindOdCarSetView;
import com.ts.can.landwind.od.CanLandWindOdTpmsView;
import com.ts.can.landwind.rzc.CanLandWindCarSetView;
import com.ts.can.landwind.rzc.CanLandWindTpmsView;
import com.ts.can.lexus.lz.CanLexusLZIs250AmpSetView;
import com.ts.can.lexus.zmyt.CanLexusZMYTCarFuncView;
import com.ts.can.lexus.zmyt.CanLexusZMYTCarInitView;
import com.ts.can.lexus.zmyt.CanLexusZMYTUpdateView;
import com.ts.can.lexus.zmyt.h.CanLexushZmytCarFuncView;
import com.ts.can.lexus.zmyt.h.CanLexushZmytCarInitView;
import com.ts.can.lexus.zmyt.h.CanLexushZmytUpdateView;
import com.ts.can.luxgen.od.CanLuxgenOdCarSetView;
import com.ts.can.luxgen.wc.CanLuxgenWCCarMainTainView;
import com.ts.can.luxgen.wc.CanLuxgenWCCarSetView;
import com.ts.can.mahindra.wc.CanMahindraWcCarSetView;
import com.ts.can.mitsubishi.outlander.CanMitSubiShiOutLanderCarSetView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcCarSetView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcDriveInfoView;
import com.ts.can.mitsubishi.rzc.CanMitsubshiRzcSetAmpView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrCarOilView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrCarSetupView;
import com.ts.can.mzd.cx4.bnr.CanMzdCx4BnrDetailInfoView;
import com.ts.can.mzd.hc.CanMzdHcKeySetView;
import com.ts.can.mzd.lz.CanMzdLzKeySetView;
import com.ts.can.mzd.rzc.CanMzdRzcCarOilView;
import com.ts.can.mzd.rzc.CanMzdRzcCarSetView;
import com.ts.can.mzd.rzc.CanMzdRzcCarTpmsView;
import com.ts.can.mzd.rzc.CanMzdRzcClbySetView;
import com.ts.can.mzd.rzc.CanMzdRzcDetailInfoView;
import com.ts.can.mzd.rzc.CanMzdRzcServiceInfoView;
import com.ts.can.mzd.rzc.CanMzdRzcistopinfoView;
import com.ts.can.mzd.wc.CanMzdWcAmpSetView;
import com.ts.can.mzd.wc.CanMzdWcCDView;
import com.ts.can.mzd.wc.CanMzdWcCarSetView;
import com.ts.can.mzd.wc.CanMzdWcClockSetView;
import com.ts.can.mzd.wc.CanMzdWcOilView;
import com.ts.can.nissan.dj.teana.CanTeanaOldDjAmpSetView;
import com.ts.can.nissan.rzc.CanNissanRzcAmpSetView;
import com.ts.can.nissan.rzc.CanNissanRzcCarSetView;
import com.ts.can.nissan.wc.CanNissanWcAmpSetView;
import com.ts.can.nissan.wc.rich6.CanNissanRich6WcBodyInfoView;
import com.ts.can.nissan.wc.rich6.CanNissanRich6WcCarSetView;
import com.ts.can.nissan.wc.rich6.CanNissanRich6WcDriveView;
import com.ts.can.nissan.wc.rich6.CanNissanRich6WcTpmsInfoView;
import com.ts.can.nissan.xc.teana.CanTeanaOldXcAmpSetView;
import com.ts.can.nissan.xc.teana.CanTeanaOldXcCarSetView;
import com.ts.can.obd.dst.CanSciDstCarSetView;
import com.ts.can.obd.dst.CanSciDstTPMSView;
import com.ts.can.porsche.lz.CanPorscheLzSetView;
import com.ts.can.porsche.lz.CanPorscheLzStatueView;
import com.ts.can.porsche.od.CanPorscheOdSetView;
import com.ts.can.porsche.od.CanPorscheOdStatueView;
import com.ts.can.porsche.od.CanPorscheOdTPMSView;
import com.ts.can.psa.CanPSAAirSetView;
import com.ts.can.psa.CanPSAAmpSetView;
import com.ts.can.psa.rzc.CanPSARzcAirSetView;
import com.ts.can.psa.rzc.CanPSARzcAmpSetView;
import com.ts.can.psa.rzc.CanPSARzcCheckInfoView;
import com.ts.can.psa.rzc.CanPSARzcCruiseSpeedView;
import com.ts.can.psa.rzc.CanPSARzcDriveInfoView;
import com.ts.can.psa.rzc.CanPSARzcFuncInfoView;
import com.ts.can.psa.rzc.CanPSARzcLangView;
import com.ts.can.psa.rzc.CanPSARzcMemTabView;
import com.ts.can.psa.rzc.CanPSARzcSpeedLimitView;
import com.ts.can.psa.rzc.CanPSARzcWarnInfoView;
import com.ts.can.psa.rzc.scr.CanPSAScrRzcAmpSetView;
import com.ts.can.psa.rzc.scr.CanPSAScrRzcDriveInfoView;
import com.ts.can.psa.rzc.scr.CanPSAScrRzcKeySetView;
import com.ts.can.psa.wc.CanPSAWCAmpSetView;
import com.ts.can.psa.wc.CanPSAWCCarSetView;
import com.ts.can.psa.wc.CanPSAWCCarTypeView;
import com.ts.can.psa.wc.CanPSAWCCruiseSpeedView;
import com.ts.can.psa.wc.CanPSAWCDriveInfoView;
import com.ts.can.psa.wc.CanPSAWCKeySetView;
import com.ts.can.psa.wc.CanPSAWCLangSetInfoView;
import com.ts.can.psa.wc.CanPSAWCMemTabView;
import com.ts.can.psa.wc.CanPSAWCSportModeView;
import com.ts.can.psa.wc.CanPSAWCUnitSetInfoView;
import com.ts.can.psa.wc.CanPSAWCWarnInfoView;
import com.ts.can.renault.kadjar.CanKadjarHudSetView;
import com.ts.can.renault.kadjar.CanKadjarZyamSetView;
import com.ts.can.renault.renault.CanRenaultCarSetView;
import com.ts.can.renault.renault.CanRenaultDriveComputeView;
import com.ts.can.renault.wc.CanRenaultWcAdasSetView;
import com.ts.can.renault.wc.CanRenaultWcCarSetView;
import com.ts.can.renault.wc.CanRenaultWcDefaultSetView;
import com.ts.can.renault.wc.CanRenaultWcDriveAssistSetView;
import com.ts.can.renault.wc.CanRenaultWcDriveComputeView;
import com.ts.can.renault.wc.CanRenaultWcLangSetView;
import com.ts.can.renault.wc.CanRenaultWcLockSetView;
import com.ts.can.renault.wc.CanRenaultWcOtherSetView;
import com.ts.can.saic.baojun.CanBaojunRs3LightSetView;
import com.ts.can.saic.baojun.CanBaojunRs3LockSetView;
import com.ts.can.saic.baojun.CanBaojunRs3ModeSetView;
import com.ts.can.saic.baojun.CanBaojunRs3OtherSetView;
import com.ts.can.saic.dt.v80.CanDtV80BatteryGroupView;
import com.ts.can.saic.dt.v80.CanDtV80BatteryInfosView;
import com.ts.can.saic.dt.v80.CanDtV80BatteryStateView;
import com.ts.can.saic.dt.v80.CanDtV80BatteryView;
import com.ts.can.saic.dt.v80.CanDtV80BmsInfosView;
import com.ts.can.saic.mg.CanMGCarTpmsView;
import com.ts.can.saic.mg.CanMgCarAvmSetView;
import com.ts.can.saic.mg.mg6.CanMg6RzcAirSetView;
import com.ts.can.saic.mg.mg6.CanMg6RzcCDriveAssView;
import com.ts.can.saic.mg.mg6.CanMg6RzcConvSetView;
import com.ts.can.saic.mg.mg6.CanMg6RzcJsmsSetView;
import com.ts.can.saic.mg.mg6.CanMg6RzcLockSetView;
import com.ts.can.saic.mg.mg6.CanMg6RzcSystemSetView;
import com.ts.can.saic.t60_rzc.CanDtT60RzcTpmsView;
import com.ts.can.saic.wc.CanSaicRWMGWcCarAirSetView;
import com.ts.can.saic.wc.CanSaicRWMGWcCarDrivAssistSetView;
import com.ts.can.saic.wc.CanSaicRWMGWcCarLightSetView;
import com.ts.can.saic.wc.CanSaicRWMGWcSystemSetView;
import com.ts.can.saic.wc.mg_zs.CanMGZSWCCarSetView;
import com.ts.can.saic.wc.rw550.CanRW550MG6WcYBSetView;
import com.ts.can.se.rzc.dx7.CanSeDx7RzcAvmSetView;
import com.ts.can.se.rzc.dx7.CanSeDx7RzcCarSetView;
import com.ts.can.se.rzc.dx7.CanSeDx7RzcPcInfoView;
import com.ts.can.se.rzc.dx7.CanSeDx7RzcTpmsView;
import com.ts.can.sitechdev.cw.CanSitechDevCwBatteryInfoView;
import com.ts.can.sitechdev.cw.CanSitechDevCwBmsWarnInfoView;
import com.ts.can.sitechdev.cw.CanSitechDevCwCarSetView;
import com.ts.can.sitechdev.cw.CanSitechDevCwDisInfoView;
import com.ts.can.sitechdev.cw.CanSitechDevCwDtInfoView;
import com.ts.can.sitechdev.cw.CanSitechDevCwSetView;
import com.ts.can.sitechdev.cw.CanSitechDevCwTpmsInfoView;
import com.ts.can.sitechdev.cw.CanSitechDevCwUpdateView;
import com.ts.can.subuar.xp.CanSubuarAmpSetView;
import com.ts.can.subuar.xp.CanSubuarCarSetView;
import com.ts.can.subuar.xp.CanSubuarDrivingAidsView;
import com.ts.can.swm.rzc.CanSwmRzcAssistSetView;
import com.ts.can.swm.rzc.CanSwmRzcAvmSetView;
import com.ts.can.swm.rzc.CanSwmRzcCarSetView;
import com.ts.can.tata.lz.CanTataLzCarSetView;
import com.ts.can.tata.wc.CanTataWcCarSetView;
import com.ts.can.toyota.dj.CanToyotaDJAmpSetView;
import com.ts.can.toyota.dj.CanToyotaDJRearSystemView;
import com.ts.can.volvo.lz.CanVolvoLZAmpSetView;
import com.ts.can.volvo.lz.CanVolvoLZCarAirSetView;
import com.ts.can.volvo.lz.CanVolvoLZCarDrivAssistSetView;
import com.ts.can.volvo.lz.CanVolvoLZCarLightSetView;
import com.ts.can.volvo.lz.CanVolvoLZSystemSetView;
import com.ts.can.volvo.lz.CanVolvoLZVinInfoView;
import com.ts.can.vw.golf.wc.CanGolfWcCDSZSetView;
import com.ts.can.vw.golf.wc.CanGolfWcHHDLSTView;
import com.ts.can.vw.golf.wc.CanGolfWcJSMSSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcConvConsumersView;
import com.ts.can.vw.rzc.golf.CanGolfRzcDrivingDataView;
import com.ts.can.vw.rzc.golf.CanGolfRzcEModeView;
import com.ts.can.vw.rzc.golf.CanGolfRzcEleDZXSView;
import com.ts.can.vw.rzc.golf.CanGolfRzcElecJsmsView;
import com.ts.can.vw.rzc.golf.CanGolfRzcLangSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcNlhsSetView;
import com.ts.can.vw.rzc.golf.CanGolfRzcSeatDriveProfileView;
import com.ts.can.vw.rzc.golf.CanGolfRzcSetMainView;
import com.ts.can.vw.rzc.golf.CanGolfRzcTeramontSeatDriveProfileView;
import com.ts.can.vw.rzc.golf.CanGolfRzcVehicleStatusView;
import com.ts.can.zh.wc.v3h3.CanZhWcCarInfoSetView;
import com.ts.can.zotye.e200.CanZotyeE200RzcDriveInfoView;
import com.ts.can.zotye.x5.wc.CanZoyteX5WcTpmsView;

public class CanCarInfoSub1Activity extends CanBaseCarInfoActivity {
    /* JADX WARNING: type inference failed for: r8v0, types: [com.ts.can.CanCarInfoSub1Activity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void InitView(int canType, int id) {
        Log.d("HAHA", "id = " + id);
        switch (canType) {
            case 2:
                if (id == 1) {
                    this.mBaseView = new CanGolfRzcDrivingDataView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanGolfRzcConvConsumersView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanGolfRzcVehicleStatusView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanGolfRzcEleDZXSView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanGolfRzcSetMainView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanGolfRzcLangSetView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new CanGolfRzcNlhsSetView(this);
                    return;
                } else if (id == -6) {
                    this.mBaseView = new CanGolfRzcEModeView(this);
                    return;
                } else if (id == -7) {
                    this.mBaseView = new CanGolfRzcTeramontSeatDriveProfileView(this);
                    return;
                } else if (id == -8) {
                    this.mBaseView = new CanGolfRzcSeatDriveProfileView(this);
                    return;
                } else if (id == -9) {
                    this.mBaseView = new CanGolfRzcElecJsmsView(this);
                    return;
                } else {
                    return;
                }
            case 5:
                if (id == 1) {
                    this.mBaseView = new CanHondaYiBiaoSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanHondaQJYXSystemSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanHonda360PanoramaSetView(this);
                    return;
                } else {
                    return;
                }
            case 8:
                if (id == 0) {
                    this.mBaseView = new CanGMHybridView(this);
                    return;
                }
                return;
            case 11:
            case 58:
                if (id == 0) {
                    this.mBaseView = new CanPSAAmpSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanPSAAirSetView(this);
                    return;
                } else {
                    return;
                }
            case 13:
                if (id == 0) {
                    this.mBaseView = new CanFordF150CarSetView(this);
                    return;
                }
                return;
            case 14:
                if (id == 1) {
                    this.mBaseView = new CanHyundaiBocheNaviSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanHyundaiCarSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanHyundaiAmpSetView(this);
                    return;
                } else {
                    return;
                }
            case 16:
                if (id == 0) {
                    this.mBaseView = new CanBaojunRs3ModeSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanBaojunRs3LightSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanBaojunRs3LockSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanBaojunRs3OtherSetView(this);
                    return;
                } else {
                    return;
                }
            case 17:
                if (id == 1) {
                    this.mBaseView = new CanGqcqNLXXSetView(this);
                    return;
                }
                return;
            case 26:
                if (id == -1) {
                    this.mBaseView = new CanMgCarAvmSetView(this);
                    return;
                } else if (id == 0) {
                    this.mBaseView = new CanMGCarTpmsView(this);
                    return;
                } else if (id == 10) {
                    this.mBaseView = new CanMg6RzcLockSetView(this);
                    return;
                } else if (id == 11) {
                    this.mBaseView = new CanMg6RzcConvSetView(this);
                    return;
                } else if (id == 12) {
                    this.mBaseView = new CanMg6RzcAirSetView(this);
                    return;
                } else if (id == 13) {
                    this.mBaseView = new CanMg6RzcCDriveAssView(this);
                    return;
                } else if (id == 14) {
                    this.mBaseView = new CanMg6RzcSystemSetView(this);
                    return;
                } else if (id == 15) {
                    this.mBaseView = new CanMg6RzcJsmsSetView(this);
                    return;
                } else {
                    return;
                }
            case 27:
                if (id == 2) {
                    this.mBaseView = new CanJACCarSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanJACCarInfoView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanJACChargSetView(this);
                    return;
                } else {
                    return;
                }
            case 29:
                if (CanJni.GetCanFsTp() == 38) {
                    if (id == 1) {
                        this.mBaseView = new CanRZygSetSuspensionView(this);
                        return;
                    } else if (id == 2) {
                        this.mBaseView = new CanRZygSetXnkzxtView(this);
                        return;
                    } else if (id == 3) {
                        this.mBaseView = new CanRZygSetWiperMirrorView(this);
                        return;
                    } else {
                        this.mBaseView = new CanRZygPanControlView(this);
                        return;
                    }
                } else if (id == 0) {
                    this.mBaseView = new CanChrOthSetSuspensionView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanChrOthAmpSetView(this);
                    return;
                } else {
                    return;
                }
            case 49:
                if (id == 0) {
                    this.mBaseView = new CanSenovaCarAssistSetView(this);
                    return;
                }
                return;
            case 52:
                if (id == 1) {
                    this.mBaseView = new CanDFCarSetView(this);
                    return;
                }
                return;
            case 55:
                if (id == 0) {
                    this.mBaseView = new CanKadjarZyamSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanKadjarHudSetView(this);
                    return;
                } else {
                    return;
                }
            case 56:
                if (id == 0) {
                    this.mBaseView = new CanMitSubiShiOutLanderCarSetView(this);
                    return;
                }
                return;
            case 60:
                if (id == 0) {
                    this.mBaseView = new CanZotyeE200RzcDriveInfoView(this);
                    return;
                }
                return;
            case 65:
                if (id == -1) {
                    this.mBaseView = new CanBMWMiniUpdateView(this);
                    return;
                }
                return;
            case 70:
                if (id == 0) {
                    this.mBaseView = new CanDFFGRzcDjxxSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanDFFGRzcDcxxSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanDFFGRzcCsxxSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanDFFGRzcCarSetView(this);
                    return;
                } else {
                    return;
                }
            case 72:
                if (id == 0) {
                    this.mBaseView = new CanGeelyRzcPM25View(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanGeelyX3TpmsView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanGeelyRzcCarAvmView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanGeelyRzcMixInfoView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanGeelyRzcCarInfoView(this);
                    return;
                } else {
                    return;
                }
            case 73:
                if (id == 0) {
                    this.mBaseView = new CanCs75CameraSetView(this);
                    return;
                }
                return;
            case 78:
                if (id == -1) {
                    this.mBaseView = new CanCCH6RzcCarSeatSetView(this);
                    return;
                } else if (id == -2) {
                    this.mBaseView = new CanCCH6RzcCarSeatStatusView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanCcRzcDrivAssistSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanCcRzcAvmSetView(this);
                    return;
                } else {
                    return;
                }
            case 86:
                if (id == 0) {
                    this.mBaseView = new CanHtX5LightSetView(this);
                    return;
                }
                return;
            case 98:
                if (id == 0) {
                    this.mBaseView = new CanBaicEc5CarSetView(this);
                    return;
                }
                return;
            case 102:
                if (id == 1) {
                    this.mBaseView = new CanHyundaiRzcParkingSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanHyundaiRzcAirSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanHyundaiRzcLightSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanHyundaiRzcYyszSetView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanHyundaiRzcEcoSetView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanHyundaiRzcNyxxView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanHyunDaiRzcNllctView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new CanHyundaiRzcCdszSetView(this);
                    return;
                } else if (id == 9) {
                    this.mBaseView = new CanHyundaiRzcJsmsSetView(this);
                    return;
                } else {
                    return;
                }
            case 110:
                if (id == 2) {
                    this.mBaseView = new CanJACRefineWcDrivAssistSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanJACRefineWcNLXXSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanJACRefineWcCDZTSetView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanJACRefineWcCarSetView(this);
                    return;
                } else {
                    return;
                }
            case 117:
                if (id == 0) {
                    this.mBaseView = new CanRenaultDriveComputeView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanRenaultCarSetView(this);
                    return;
                } else {
                    return;
                }
            case 122:
                if (id == 1) {
                    this.mBaseView = new CanGolfCarAirSetView(this);
                    return;
                }
                return;
            case 126:
                if (id == 1) {
                    this.mBaseView = new CanDtT60RzcTpmsView(this);
                    return;
                }
                return;
            case 127:
                if (id == 1) {
                    this.mBaseView = new CanPSARzcDriveInfoView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanPSARzcCheckInfoView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanPSARzcWarnInfoView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanPSARzcFuncInfoView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanPSARzcCruiseSpeedView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanPSARzcSpeedLimitView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanPSARzcMemTabView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new CanPSARzcLangView(this);
                    return;
                } else if (id == 9) {
                    this.mBaseView = new CanPSARzcAmpSetView(this);
                    return;
                } else if (id == 10) {
                    this.mBaseView = new CanPSARzcAirSetView(this);
                    return;
                } else {
                    return;
                }
            case 128:
                if (id == -1) {
                    this.mBaseView = new CanToyotaRearDisplayView(this);
                    return;
                }
                return;
            case Can.CAN_BENC_ZMYT:
                if (id == 0) {
                    this.mBaseView = new CanBencWithCDCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanBencWithCDAutoBreakView(this);
                    return;
                } else {
                    return;
                }
            case 142:
                if (id == -1) {
                    this.mBaseView = new CanGolfWcHHDLSTView(this);
                    return;
                } else if (id == 0) {
                    this.mBaseView = new CanGolfWcCDSZSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanGolfWcJSMSSetView(this);
                    return;
                } else {
                    return;
                }
            case 146:
                if (id == 3) {
                    this.mBaseView = new CanFordRzcCarSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanFordRzcSetLightView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanFordRzcAmpSetView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanFordRzcTpmsView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanFordRzcChairSetView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new CanFordRzcChargeMixView(this);
                    return;
                } else if (id == 9) {
                    this.mBaseView = new CanFordRzcZnxfSetView(this);
                    return;
                } else if (id == 10) {
                    this.mBaseView = new CanFordRzcDriveAssistSetView(this);
                    return;
                } else if (id == 11) {
                    this.mBaseView = new CanFordRzcLockSetView(this);
                    return;
                } else if (id == 12) {
                    this.mBaseView = new CanFordRzcOtherSetView(this);
                    return;
                } else {
                    return;
                }
            case 147:
                if (id == 0) {
                    this.mBaseView = new CanLandWindCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanLandWindTpmsView(this);
                    return;
                } else {
                    return;
                }
            case 148:
                if (id == 0) {
                    this.mBaseView = new CanPSAWCCarTypeView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanPSAWCCarSetView(this);
                    return;
                } else if (id == 2) {
                    if (this.mBaseView == null || !(this.mBaseView instanceof CanPSAWCDriveInfoView)) {
                        this.mBaseView = new CanPSAWCDriveInfoView(this);
                        return;
                    } else {
                        ((CanPSAWCDriveInfoView) this.mBaseView).PageInc();
                        return;
                    }
                } else if (id == 3) {
                    this.mBaseView = new CanPSAWCWarnInfoView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanPSAWCCruiseSpeedView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanPSAWCMemTabView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanPSAWCSportModeView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanPSAWCLangSetInfoView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new CanPSAWCUnitSetInfoView(this);
                    return;
                } else if (id == 9) {
                    this.mBaseView = new CanPSAWCKeySetView(this);
                    return;
                } else if (id == 10) {
                    this.mBaseView = new CanPSAWCAmpSetView(this);
                    return;
                } else {
                    return;
                }
            case 149:
                this.mBaseView = new CanNissanWcAmpSetView(this);
                return;
            case 150:
                if (id == 0) {
                    this.mBaseView = new CanJACRefineOdCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanJACRefineOdTpmsView(this);
                    return;
                } else {
                    return;
                }
            case 151:
                if (id == -1) {
                    this.mBaseView = new CanGMWcOnStarSpkView(this);
                    return;
                } else if (id == 0) {
                    this.mBaseView = new CanGMWcCarTypeView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanGMWcSetLockView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanGMWcSetACView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanGMWcSetLightView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanGMWcSetConvView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanGMWcSetCDSView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanGMWcVehicleInfoView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanGMWcCarStatusView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new CanGMWcTPMSView(this);
                    return;
                } else if (id == 9) {
                    this.mBaseView = new CanGMWcSetOtherView(this);
                    return;
                } else if (id == 10) {
                    this.mBaseView = new CanGMWcSetLanguageView(this);
                    return;
                } else {
                    return;
                }
            case 152:
                if (id == 0) {
                    this.mBaseView = new CanAudiWithCDCarInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanAudiWithCDCarFuncView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanAudiWithCDCarInitView(this);
                    return;
                } else {
                    return;
                }
            case 153:
                if (id == 0) {
                    this.mBaseView = new CanHyundaiWcSetAmpView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanHyundaiWc360CameraSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanHyundaiWcCarSetView(this);
                    return;
                } else {
                    return;
                }
            case Can.CAN_CC_WC:
                if (id == 0) {
                    this.mBaseView = new CanCCWcCarTypeView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanCCWcAmpSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanCCWcCarSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanCCWcLangSetView(this);
                    return;
                } else {
                    return;
                }
            case Can.CAN_HONDA_WC:
                if (id == -1) {
                    this.mBaseView = new CanHondaWcConsumpHisView(this);
                    return;
                } else if (id == 0) {
                    return;
                } else {
                    if (id == 1) {
                        this.mBaseView = new CanHondaWcConsumpCurView(this);
                        return;
                    } else if (id == 2) {
                        this.mBaseView = new CanHondaWcLightLockSetView(this);
                        return;
                    } else if (id == 3) {
                        this.mBaseView = new CanHondaWcRemoteLockSetSetView(this);
                        return;
                    } else if (id == 4) {
                        this.mBaseView = new CanHondaWcCarLockSetView(this);
                        return;
                    } else if (id == 5) {
                        this.mBaseView = new CanHondaWcCarDrivAssistSetView(this);
                        return;
                    } else if (id == 6) {
                        this.mBaseView = new CanHondaWcCarDistanceSetView(this);
                        return;
                    } else if (id == 7) {
                        this.mBaseView = new CanHondaWcMotoRearDoorSetView(this);
                        return;
                    } else if (id == 8) {
                        this.mBaseView = new CanHondaWcSystemSetView(this);
                        return;
                    } else if (id == 9) {
                        this.mBaseView = new CanHondaWcAmpSetView(this);
                        return;
                    } else if (id == 10) {
                        this.mBaseView = new CanHondaWcCameraSetView(this);
                        return;
                    } else {
                        return;
                    }
                }
            case Can.CAN_FORD_WC:
                if (id == 0) {
                    this.mBaseView = new CanFordWcCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanFordWcWarnInfoView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanFordWcLangSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanFordWcAmpSetView(this);
                    return;
                } else if (id != -1) {
                    return;
                } else {
                    if (this.mBaseView == null || !(this.mBaseView instanceof CanFordWcActiveParkView)) {
                        this.mBaseView = new CanFordWcActiveParkView(this);
                        return;
                    }
                    return;
                }
            case Can.CAN_DF_WC:
                if (id == 0) {
                    this.mBaseView = new CanDfFsWcDriveInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanDfFsWcCarSetView(this);
                    return;
                } else {
                    return;
                }
            case 160:
                if (id == 0) {
                    this.mBaseView = new CanCs75WcVehicleInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanCs75WcCarSetActivity(this);
                    return;
                } else {
                    return;
                }
            case 161:
                if (id == 0) {
                    this.mBaseView = new CanTrumpchiWcLangSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanTrumpchiWcACSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanTrumpchiWcChairSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanTrumpchiWcDrvAssSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanTrumpchiWcAttachSetView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanTrumpchiWcLightSetView(this);
                    return;
                } else if (id == -1) {
                    this.mBaseView = new CanTrumpchiWcLinkSosView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanTrumpchiWcNLSetView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanTrumpchiWcChargSetView(this);
                    return;
                } else {
                    return;
                }
            case 162:
                if (id == -1) {
                    this.mBaseView = new CanChryslerWcCDView(this);
                    return;
                } else if (id == 0) {
                    return;
                } else {
                    if (id == 1) {
                        this.mBaseView = new CanChryslerWcCarDrivAssistSetView(this);
                        return;
                    } else if (id == 2) {
                        this.mBaseView = new CanChryslerWcLightLockSetView(this);
                        return;
                    } else if (id == 3) {
                        this.mBaseView = new CanChryslerWcSystemSetView(this);
                        return;
                    } else if (id == 4) {
                        this.mBaseView = new CanChryslerWcCarLockSetView(this);
                        return;
                    } else if (id == 5) {
                        this.mBaseView = new CanChryslerWcAmpSetView(this);
                        return;
                    } else if (id == 6) {
                        this.mBaseView = new CanChryslerWcACSetView(this);
                        return;
                    } else {
                        return;
                    }
                }
            case 163:
                if (id == 0) {
                    this.mBaseView = new CanBMWX1WcDriveComputeView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanBMWX1WcZCTFView(this);
                    return;
                } else {
                    return;
                }
            case 164:
                if (id == -1) {
                    this.mBaseView = new CanMzdWcCDView(this);
                    return;
                } else if (id == 0) {
                    this.mBaseView = new CanMzdWcCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanMzdWcAmpSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanMzdWcClockSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanMzdWcOilView(this);
                    return;
                } else {
                    return;
                }
            case 165:
                if (id == 0) {
                    this.mBaseView = new CanBencMetrisWcCarSetView(this);
                    return;
                }
                return;
            case 167:
                if (id == 0) {
                    this.mBaseView = new CanDtV80BatteryGroupView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanDtV80BatteryView(this);
                    return;
                } else if (id == 2) {
                    if (CanJni.GetSubType() == 2) {
                        this.mBaseView = new CanDtV80BatteryStateView(this);
                        return;
                    } else {
                        this.mBaseView = new CanDtV80BatteryInfosView(this);
                        return;
                    }
                } else if (id == 3) {
                    this.mBaseView = new CanDtV80BmsInfosView(this);
                    return;
                } else {
                    return;
                }
            case 168:
                if (id == 0) {
                    this.mBaseView = new CanCrownWcAmpSetView(this);
                    return;
                }
                return;
            case 169:
                if (id == 0) {
                    this.mBaseView = new CanJeepWcAmpSetView(this);
                    return;
                }
                return;
            case 172:
                if (id == 0) {
                    this.mBaseView = new CanWcH6LightSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanWcH6CarSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanWcH6AVMSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanWcH6AmpSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanWcH6LangSetView(this);
                    return;
                } else {
                    return;
                }
            case 173:
                if (id == 0) {
                    this.mBaseView = new CanCompassWcAmpSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanCompassWcUnitSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanCompassWcSafeAssSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanCompassWcLockSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanCompassWcLightSetView(this);
                    return;
                } else {
                    return;
                }
            case 174:
                if (id == 0) {
                    this.mBaseView = new CanCCH2WcCameraSetView(this);
                    return;
                }
                return;
            case 176:
                if (id == 0) {
                    this.mBaseView = new CanBmwZmytWithCDCarInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanBmwZmytWithCDCarFuncView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanBmwZmytWithCDCarInitView(this);
                    return;
                } else {
                    return;
                }
            case 177:
                if (id == 1) {
                    this.mBaseView = new CanSaicRWMGWcCarDrivAssistSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanSaicRWMGWcCarLightSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanSaicRWMGWcCarAirSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanSaicRWMGWcSystemSetView(this);
                    return;
                } else {
                    return;
                }
            case 180:
                if (id == 1) {
                    this.mBaseView = new CanCheryWcCarDrivAssistSetView(this);
                    return;
                }
                return;
            case 191:
                if (id == 0) {
                    this.mBaseView = new CanZoyteX5WcTpmsView(this);
                    return;
                }
                return;
            case 194:
                if (id == 1) {
                    this.mBaseView = new CanFordMondeoWcDrivAssistSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanFordMondeoWcSystemSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanFordMondeoWcCarSetView(this);
                    return;
                } else {
                    return;
                }
            case 196:
                if (id == -1) {
                    this.mBaseView = new CanAccord9WcConsumpHisView(this);
                    return;
                } else if (id == 0) {
                    this.mBaseView = new CanAccord9WcConsumpCurView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanAccord9WcCarLockSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanAccord9WcLightLockSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanAccord9WcCarDrivAssistSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanAccord9WcDistanceSetView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanAccord9WcSystemSetView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanAccord9WcCameraSetView(this);
                    return;
                } else {
                    return;
                }
            case 197:
                if (id == 0) {
                    this.mBaseView = new CanBaicHSS6WcAirSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanBaicHSS6WcCarInfoSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanBaicHSS6WcYiBiaoSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanBaicHSS6WcCameraSetView(this);
                    return;
                } else {
                    return;
                }
            case 198:
                if (id == 0) {
                    this.mBaseView = new CanPorscheOdStatueView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanPorscheOdSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanPorscheOdTPMSView(this);
                    return;
                } else {
                    return;
                }
            case 199:
                if (id == 1) {
                    this.mBaseView = new CanNissanRzcAmpSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanNissanRzcCarSetView(this);
                    return;
                } else {
                    return;
                }
            case 203:
                if (id == 0) {
                    this.mBaseView = new CanSciDstCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanSciDstTPMSView(this);
                    return;
                } else {
                    return;
                }
            case 204:
                if (id == 0) {
                    this.mBaseView = new CanMzdLzKeySetView(this);
                    return;
                }
                return;
            case Can.CAN_LEXUS_IZ:
                if (id == 0) {
                    this.mBaseView = new CanLexusLZIs250AmpSetView(this);
                    return;
                }
                return;
            case Can.CAN_SAIL_RW550_MG6_WC:
                if (id == 1) {
                    this.mBaseView = new CanRW550MG6WcYBSetView(this);
                    return;
                }
                return;
            case Can.CAN_LEXUS_ZMYT:
                if (id == 0) {
                    this.mBaseView = new CanLexusZMYTCarFuncView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanLexusZMYTCarInitView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanLexusZMYTUpdateView(this);
                    return;
                } else {
                    return;
                }
            case 210:
            case 263:
                if (id == 0) {
                    this.mBaseView = new CanGMCarTypeView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanGMSetLockView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanGMSetACView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanGMSetLightView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanGMSetConvView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanGMSetCDSView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanGMSetOtherView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanGMSetLanguageView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new com.ts.can.gm.rzc.CanGMHybridView(this);
                    return;
                } else if (id == 9) {
                    this.mBaseView = new CanGMOpelKeyView(this);
                    return;
                } else if (id == 10) {
                    this.mBaseView = new CanGMRzcCarInfoView(this);
                    return;
                } else if (id == -1) {
                    this.mBaseView = new CanGL8RearACView(this);
                    return;
                } else if (id == -2) {
                    this.mBaseView = new CanGMActivityParkView(this);
                    return;
                } else if (id == -3) {
                    this.mBaseView = new CanOnStarWifiView(this);
                    return;
                } else {
                    return;
                }
            case 212:
                if (id == 0) {
                    this.mBaseView = new CanHantElectCarDriveSetView(this);
                }
                if (id == 1) {
                    this.mBaseView = new CanHantElectCarWarnInfoView(this);
                }
                if (id == 2) {
                    this.mBaseView = new CanHantElectCarKeyLockSetView(this);
                }
                if (id == 3) {
                    this.mBaseView = new CanHantElectCarBaseInfoSetView(this);
                    return;
                }
                return;
            case 214:
                if (id == 0) {
                    this.mBaseView = new CanChanaWcCarSetView(this);
                    return;
                }
                return;
            case 216:
                if (id == 0) {
                    this.mBaseView = new CanRenaultWcDriveComputeView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanRenaultWcCarSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanRenaultWcLangSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanRenaultWcAdasSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanRenaultWcDriveAssistSetView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanRenaultWcLockSetView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanRenaultWcOtherSetView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanRenaultWcDefaultSetView(this);
                    return;
                } else {
                    return;
                }
            case 217:
                if (id == 0) {
                    this.mBaseView = new CanLuxgenOdCarSetView(this);
                    return;
                }
                return;
            case 221:
                if (id == 0) {
                    this.mBaseView = new CanPorscheLzStatueView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanPorscheLzSetView(this);
                    return;
                } else {
                    return;
                }
            case Can.CAN_JIANGLING_MYX:
                if (id == 0) {
                    this.mBaseView = new CanJiangLingMyxCarSetView(this);
                    return;
                }
                return;
            case Can.CAN_TEANA_OLD_DJ:
                if (id == 0) {
                    this.mBaseView = new CanTeanaOldDjAmpSetView(this);
                    return;
                }
                return;
            case Can.CAN_CC_HF_DJ:
                if (id == 0) {
                    this.mBaseView = new CanHfDjCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanHfDjCarStatusView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanHfDjCarPdInfoView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanHfDjSeatStatusView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanHfDjAmpSetView(this);
                    return;
                } else {
                    return;
                }
            case Can.CAN_BYD_S6_S7:
                if (id == 0) {
                    this.mBaseView = new CanBydS6S7CarWinSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanBydS6S7PM25SetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanBydS6S7AmpSetView(this);
                    return;
                } else {
                    return;
                }
            case Can.CAN_ZH_WC:
                if (id == 0) {
                    this.mBaseView = new CanZhWcCarInfoSetView(this);
                    return;
                }
                return;
            case Can.CAN_NISSAN_RICH6_WC:
                if (id == 0) {
                    this.mBaseView = new CanNissanRich6WcBodyInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanNissanRich6WcTpmsInfoView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanNissanRich6WcCarSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanNissanRich6WcDriveView(this);
                    return;
                } else {
                    return;
                }
            case Can.CAN_SE_DX7_RZC:
                if (id == 0) {
                    this.mBaseView = new CanSeDx7RzcCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanSeDx7RzcAvmSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanSeDx7RzcPcInfoView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanSeDx7RzcTpmsView(this);
                    return;
                } else {
                    return;
                }
            case Can.CAN_GM_CAPTIVA_OD:
                if (id == 0) {
                    this.mBaseView = new CanGmCaptivaOdPcInfoView(this);
                    return;
                }
                return;
            case Can.CAN_SITECHDEV_CW:
                if (id == 1) {
                    this.mBaseView = new CanSitechDevCwCarSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanSitechDevCwDisInfoView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanSitechDevCwTpmsInfoView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanSitechDevCwSetView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanSitechDevCwDtInfoView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanSitechDevCwBatteryInfoView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new CanSitechDevCwBmsWarnInfoView(this);
                    return;
                } else if (id == 9) {
                    this.mBaseView = new CanSitechDevCwUpdateView(this);
                    return;
                } else {
                    return;
                }
            case Can.CAN_BYD_M6_DJ:
                if (id == 0) {
                    this.mBaseView = new CanBydM6DjCarSetView(this);
                    return;
                }
                return;
            case Can.CAN_CHRYSLER_TXB:
                if (id == 0) {
                    this.mBaseView = new CanChryslerTxbAmpSetView(this);
                    return;
                }
                return;
            case Can.CAN_LUXGEN_WC:
                if (id == 0) {
                    this.mBaseView = new CanLuxgenWCCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanLuxgenWCCarMainTainView(this);
                    return;
                } else {
                    return;
                }
            case Can.CAN_MG_ZS_WC:
                if (id == 0) {
                    this.mBaseView = new CanMGZSWCCarSetView(this);
                    return;
                }
                return;
            case Can.CAN_FLAT_RZC:
                if (id == 0) {
                    this.mBaseView = new CanFiatRzcCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanFiatRzcDriveInfoView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanFiatRzcLangSetInfoView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanFiatRzcUnitSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanFiatRzcLightSetView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanFiatRzcLockSetView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanFiatRzcParkAssistSetView(this);
                    return;
                } else {
                    return;
                }
            case 255:
                if (id == 0) {
                    this.mBaseView = new CanChanACosCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanChanACosTPMSView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanChanACosDvrSetView(this);
                    return;
                } else {
                    return;
                }
            case 256:
                if (id == 0) {
                    this.mBaseView = new CanMzdCx4BnrDetailInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanMzdCx4BnrCarSetupView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanMzdCx4BnrCarOilView(this);
                    return;
                } else {
                    return;
                }
            case 257:
                if (id == 0) {
                    this.mBaseView = new CanVolvoLZCarDrivAssistSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanVolvoLZCarLightSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanVolvoLZCarAirSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanVolvoLZSystemSetView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanVolvoLZVinInfoView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanVolvoLZAmpSetView(this);
                    return;
                } else {
                    return;
                }
            case 258:
                if (id == 0) {
                    this.mBaseView = new CanJYX5WcLockSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanJYX5WcLightSetView(this);
                    return;
                } else {
                    return;
                }
            case 260:
                if (id == 0) {
                    this.mBaseView = new CanMzdRzcDetailInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanMzdRzcCarSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanMzdRzcCarOilView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanMzdRzcistopinfoView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanMzdRzcCarTpmsView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanMzdRzcClbySetView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanMzdRzcServiceInfoView(this);
                    return;
                } else {
                    return;
                }
            case 262:
                if (id == 0) {
                    this.mBaseView = new CanFordFiestaWcCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanFordFiestaWcWarnInfoView(this);
                    return;
                } else {
                    return;
                }
            case 264:
                if (id == 0) {
                    this.mBaseView = new CanFiatWcCarSetView(this);
                    return;
                } else if (id == 1) {
                    if (this.mBaseView == null || !(this.mBaseView instanceof CanFiatWcDriveInfoView)) {
                        this.mBaseView = new CanFiatWcDriveInfoView(this);
                        return;
                    } else {
                        ((CanFiatWcDriveInfoView) this.mBaseView).PageInc();
                        return;
                    }
                } else if (id == 2) {
                    this.mBaseView = new CanFiatWcLangSetInfoView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanFiatWcUnitSetView(this);
                    return;
                } else {
                    return;
                }
            case 265:
                if (id == 0) {
                    this.mBaseView = new CanMitsubshiRzcSetAmpView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanMitsubshiRzcDriveInfoView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanMitsubshiRzcCarSetView(this);
                    return;
                } else {
                    return;
                }
            case 266:
                if (id == 0) {
                    this.mBaseView = new CanToyotaDJAmpSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanToyotaDJRearSystemView(this);
                    return;
                } else {
                    return;
                }
            case 267:
                if (id == 0) {
                    this.mBaseView = new CanDfAx7OdCarAvmSetView(this);
                    return;
                }
                return;
            case 268:
                if (id == 0) {
                    this.mBaseView = new CanBydRswCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanBydRswPm25SetView(this);
                    return;
                } else {
                    return;
                }
            case 269:
                if (id == 1) {
                    this.mBaseView = new CanChanaODAirSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanChanaODCarSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanChanaODCSXXView(this);
                    return;
                } else {
                    return;
                }
            case 270:
                if (id == 0) {
                    this.mBaseView = new CanHondaBnrCarDrivAssistSetView(this);
                    return;
                }
                return;
            case 273:
                if (id == 0) {
                    this.mBaseView = new CanMzdHcKeySetView(this);
                    return;
                }
                return;
            case 274:
                if (id == 1) {
                    this.mBaseView = new CanBMWLzAmpSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanBMWLzCarSetView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanBMWLzConsumptionView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanBMWLzYbxxView(this);
                    return;
                } else {
                    return;
                }
            case 275:
                if (id == 0) {
                    this.mBaseView = new CanLandWindOdCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanLandWindOdTpmsView(this);
                    return;
                } else {
                    return;
                }
            case 276:
                if (id == 0) {
                    this.mBaseView = new CanLexushZmytCarFuncView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanLexushZmytCarInitView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanLexushZmytUpdateView(this);
                    return;
                } else {
                    return;
                }
            case 277:
                if (id == 1) {
                    this.mBaseView = new CanBMW2LzCarTpmsView(this);
                    return;
                }
                return;
            case 278:
                if (id == 0) {
                    this.mBaseView = new CanTataWcCarSetView(this);
                    return;
                }
                return;
            case 280:
                if (id == 0) {
                    this.mBaseView = new CanMahindraWcCarSetView(this);
                    return;
                }
                return;
            case 282:
                if (id == 0) {
                    this.mBaseView = new CanSwmRzcCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanSwmRzcAssistSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanSwmRzcAvmSetView(this);
                    return;
                } else {
                    return;
                }
            case 284:
                if (id == 0) {
                    this.mBaseView = new CanPSAScrRzcDriveInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanPSAScrRzcKeySetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanPSAScrRzcAmpSetView(this);
                    return;
                } else {
                    return;
                }
            case 285:
                if (id == 0) {
                    this.mBaseView = new CanSubuarDrivingAidsView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanSubuarCarSetView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanSubuarAmpSetView(this);
                    return;
                } else {
                    return;
                }
            case 286:
                if (id == 0) {
                    this.mBaseView = new CanFawB30T3CarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanFawB30T3UseInfoView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanFawB30T3BatteryStatusView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanFawB30T3MachineStatusView(this);
                    return;
                } else {
                    return;
                }
            case 287:
                if (id == 0) {
                    this.mBaseView = new CanKaWeiWcTpmsView(this);
                    return;
                }
                return;
            case 288:
                if (id == 0) {
                    return;
                }
                if (id == -1) {
                    this.mBaseView = new CanHondaDaRzcConsumpHistoryView(this);
                    return;
                } else if (id == 1) {
                    CanJni.HondaDAQuery(210, 0);
                    this.mBaseView = new CanHondaDaRzcCompassView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanHondaDaRzcConsumpCurrentView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanHondaDaRzcCarDistanceView(this);
                    return;
                } else if (id == 4) {
                    this.mBaseView = new CanHondaDaRzcDistanceIllView(this);
                    return;
                } else if (id == 5) {
                    this.mBaseView = new CanHondaDaRzcCarCsSetView(this);
                    return;
                } else if (id == 6) {
                    this.mBaseView = new CanHondaDaRzcRemoteLockSetView(this);
                    return;
                } else if (id == 7) {
                    this.mBaseView = new CanHondaDaRzcCarSetView(this);
                    return;
                } else if (id == 8) {
                    this.mBaseView = new CanHondaDaRzcCarDrivAssistSetView(this);
                    return;
                } else if (id == 9) {
                    this.mBaseView = new CanHondaDaRzcCarSystemSetView(this);
                    return;
                } else if (id == 10) {
                    this.mBaseView = new CanHondaDaRzcMotoRearDoorView(this);
                    return;
                } else if (id == 11) {
                    this.mBaseView = new CanHondaDaRzcAmpSetView(this);
                    return;
                } else if (id == 12) {
                    this.mBaseView = new CanHondaDaRzcCameraSetView(this);
                    return;
                } else if (id == 13) {
                    this.mBaseView = new CanHondaYiBiaoSetView(this);
                    return;
                } else if (id == 14) {
                    this.mBaseView = new CanHondaQJYXSystemSetView(this);
                    return;
                } else if (id == 15) {
                    this.mBaseView = new CanHonda360PanoramaSetView(this);
                    return;
                } else if (id == 16) {
                    this.mBaseView = new CanHondaDaRzcGllxxView(this);
                    return;
                } else {
                    return;
                }
            case 289:
                if (id == 0) {
                    this.mBaseView = new CanLandRoverZmytCarFuncView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanLandRoverZmytCarInitView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanLandRoverZmytUpdateView(this);
                    return;
                } else {
                    return;
                }
            case 292:
                if (id == 0) {
                    this.mBaseView = new CanFordDjCarSetView(this);
                    return;
                }
                return;
            case 293:
                if (id == -1) {
                    this.mBaseView = new CanB70DjCDView(this);
                    return;
                } else if (id == -2) {
                    this.mBaseView = new CanB70DjRadioView(this);
                    return;
                } else if (id == -3) {
                    this.mBaseView = new CanB70DjSetView(this);
                    return;
                } else {
                    return;
                }
            case 294:
                if (id == 0) {
                    this.mBaseView = new CanTeanaOldXcCarSetView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanTeanaOldXcAmpSetView(this);
                    return;
                } else {
                    return;
                }
            case 298:
                if (id == 0) {
                    this.mBaseView = new CanAudiLzWithCDCarInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanAudiLzWithCDCarFuncView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanAudiLzWithCDCarInitView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanAudiLzWithCDUpdateView(this);
                    return;
                } else {
                    return;
                }
            case 300:
                if (id == 2) {
                    this.mBaseView = new CanGmEnclaveWcSetACView(this);
                    return;
                } else if (id == 10) {
                    this.mBaseView = new CanGmEnclaveWcSetLanguageView(this);
                    return;
                } else {
                    return;
                }
            case 303:
                if (id == 0) {
                    this.mBaseView = new CanAudiXbsWithCDCarInfoView(this);
                    return;
                } else if (id == 1) {
                    this.mBaseView = new CanAudiXbsWithCDCarFuncView(this);
                    return;
                } else if (id == 2) {
                    this.mBaseView = new CanAudiXbsWithCDCarInitView(this);
                    return;
                } else if (id == 3) {
                    this.mBaseView = new CanAudiXbsWithCDUpdateView(this);
                    return;
                } else {
                    return;
                }
            case 307:
                if (id == 0) {
                    this.mBaseView = new CanTataLzCarSetView(this);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
