/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.reso.resocalc.Utils;
/**
 *
 * @author SHAPPN
 */
public interface WsLogDictonary {
    
    enum CalcRequest {
        calc,
        save;
    }
    
    enum PartnerType {
        INSURED(1),
        OWNER(2),
        ONEPERSON(3);
        
        private final int index;

        private PartnerType(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        } 
    }

    enum CalcCoeff {
        /**МБП Минимальная базовая премия*//**МБП Минимальная базовая премия*/
        minPremium(1),
        /**РСС Расчетная страховая сумма*/
        basicSum(2),
        /**СС Страховая сумма*/
        carCostManual(3),  
        /**ТДУ Тариф дострахования по УЩЕРБУ*/
        perDam(4),
        /**КВС Коэффициент возраст-стаж*/
        cascoMaxKvs(5),
        /**КБМ Коэффициент бонус-малус по КАСКО*/
        kbmCoeffFinalCasco(6),
        /**КСТОА Коэффициент за ремонт на СТОА*/
        cascoProgrammStoaCoeff(7),
        /**КФ Коэффициент франшизы*/
        dedDiscount(8),
        /**КСВ Коэффициент скидки по варианту*/
        programDiscount(9),
        /**КПС Коэффициент периода страхования*/
        validityCoeff(10),
        /**КНР Коэффициент надбавки за рассрочку*/
        payPeriodCoeff(11),
        /**Коэффициент региона*/
        coeffRegionDam(12),
        /**Коэффициент за кредитную организацию*/
        bankCoeff(13),
        /**КВТС Коэффициент за возраст ТС*/
        carAgeCoeff(14),
        /**КС Коэффициент скоринга*/
        autodexCoeff(15),        
        /**КА Коэффициент агента Хищение*/
        agentCoeffThf(16),
        /**КСС Коэффициент срок страхования*/
        policyPeriodCoeffThf(18),
        /**ОСАГО - Базовый тариф*/
        osagoBaseTariff(19),
        /**Базовый тариф риск "Эконом"*/
        tariffHelp(20),
        /**Базовый тариф риск "Комфорт"*/
        tariffHelpComfort(21),
        /**Базовый тариф риск "Специальный"*/
        tariffHelpSpec(22),
        /**Коэф-т скидка/надбавка*/
        discount(23),
        /**Коэффициент срока действия полиса только для ГАП*/
        validityCoeffGap(24),
        /**Ущерб - коэффициент "новый бизнес"*/
        damageNewBuisnessCoeff(25),
        /**ОСАГО - Коэфф-т КБМ*/
        kbmCoeffFinalOsago(27),
        /**Коэффициент возраст ТС (продукт ДГО)*/
        carAgeCoeffDGO(28),
        /**Коэффициент за парк ТС*/
        carParkCoeff(29),
        /**Коэффициент страхового тарифа ОСАГО*/
        insTariffOsagoCoeff(30),
        /**КАСКО. Коэффициент расширения территории покрытия (Кр)*/
        coverTerritoryCoeff(31),
        /**Коэффициент страхуется только риск "Хищение"*/
        thfOnlyCoeff(32),
        /**Дополнительный к-т  применятеся к КСВ*/
        extraCoeff(33),
        /**Коэффициент корректирующий премию для черного списка агентов*/
        agentWhiteListCorrectCoeff(34),
        /**ОСАГО - Терр.коэфф.*/
        osagoTerrCoeff(35),
        /**ОСАГО - Коэфф.периода использования*/
        osagoUsePeriodCoeff(36),
        /**ОСАГО - Коэф-т использования трейлера*/
        osagoCarUsedTrailerCoeff(37),
        /**ОСАГО - Коэфф.мощности */
        osagoPowerCoeff(38),
        /**Коэф-т возраст/стаж "ОСАГО" максимальный для списка водителей*/
        osagoMaxKvs(39),
        /**ОСАГО - Финальный коэффициент*/
        osagoFinalCoeff(40),
        /**ДГО - Коэф-т возраст/стаж максимальный для списка водителей*/
        dgoMaxKvs(41),
        /**ДГО - Коэф-т "Новый бизнес"*/
        dgoNewBusinessCoeff(42),
        /**ДГО - Коэф-т использования трейлера (прицепа)*/
        dgoCarUsedTrailerCoeff(43),
        /**Коэффициент срока страхования "РЕСО-Помощь"*/
        helpPeriodCoeff(44),
        /**НС - Коэффициент "Тарифы ЮЛ, КВ агента"*/
        accidentULAgentCommCoeff(45),
        /**Коэффициент агента*/
        agentCoeff(46),
        /**Дополнительный к-т периода страхования*/
        validityExtCoeff(47),       
        /**Коэффициент скоринга*/
        audaHistoryCoeff(49),
        /**Коэффициент программы "Зеленый свет"*/
        greenLightcoeff(50),
        /**Коэффициент нагрузки риск "Хищение"*/
        juridicalCoeffThf(51),
        /**Базовый тариф риск "Хищение"*/
        perThf(52),             
        /**Коэффициент поисковой системы*/
        thfSearchingSystemCoeff(55),       
        /**Коэффициент за безубыточность*/
        coeffKBU(57),
        /**Годовой тариф GAP*/
        tarifGap(58),       
        /**Базовый тариф "Несчастный случай"*/
        lBaseTariff(60),
        /**Страховая сумма Риск НС*/
        carAccidentPlaceInsSum(61),
        /**Кол-во мест в ТС страхуемых по системе мест Риск НС */
        carAccidentPlaceNum(62),
        /**КА Коэффициент агента риск Ущерб */
        agentCoeffDam(63),
        /**КСС Коэффициент  срок страхования Ущерб*/
        policyPeriodCoeffDam(64),
        /**Коэффициент нагрузки, риск "Ущерб"*/
        juridicalCoeffDam(65),
        /**Корректирующий коэффициент по риску "ГАП"*/
        correctCoeffGap(66),
         /**Коэффициент нагрузки, риск "ГАП"*/
        juridicalCoeffGap(67),
        /**Региональный коэффициент, риск "Столкновение"*/
        coeffRegionCrash(68),
        /**Корректирующий коэффициент для риска  "Столкновение"*/
        correctCoeffCrash(69),
        /**КА Коэффициент агента по риску Доп оборудование*/
        agentCoeffEquipment(70),
        /**К-т срок страхования  по риску "Доп. Оборудование"*/
        policyPeriodCoeffEquipment(71),
        /**Тариф  по риску "Доп. Оборудование"*/
        tariffEquipment(72),
        /**Коэффициент нагрузки, риск "Доп. Оборудование"*/
        juridicalCoeffEquipment(73),
        /**Коэффициент нагрузки, риск "Доп. Оборудование"*/
        tariffAccident(74),
        /**Тариф ДГО"*/
        tariffDGO(75),
        /**ОСАГО - КО - Коэффициент на ограничение допущенных к управлению*/
        osagoKO(76),
        /**ОСАГОКП - Коэффициент от срока страхования*/
        osagoKP(77),
        /**[KОБ] - обязательный коэффициент*/
        notAlternativeAction(78),
         /**[KФ] - Коэффициент франшизы*/
        franchiseDiscount(79),
        /**КУ Коэффициент по ущербу для юр. лиц*/
        damCoefVIP(80),
        /**КУбХ  Коэффициент надбавки за риск УЩЕРБ без ХИЩЕНИЯ */
        coeffOnlyDamage(81),
        /**[БП] - Базовая премия  CRASH */
        basePremiumCrash(82),
        /**[КВС] - Коэффициент возраста-стажа CRASH*/
        kvsCrash(83),
        /**[КСТОА] - Коэффициент за СТОА CRASH*/
        stoaCoeffCrash(84),
        /**[МРС] - Минимальная расчетная премия CRASH*/
        minPremiumCrash(85),
        /**[КПС] - Коэффициент периода страхования*/
        validityCoeffCrash(86),
        /**[КА] - Коэффициент агента CRASH*/
        agentCoeffCrash(87),
        /**Коэффициент Equifax*/
        equifaxCoeff(88);

        private final int index;

        private CalcCoeff(int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

    }
    
    enum CalcPremium {
        /**Премия по риску ущерб*/
        PREMIUMDAM(1,239347),
        /**Премия по риску хищение*/
        PREMIUMTHF(2,239348),       
        /**Премия по риску только ГАП*/
        PREMIUMGAP(4, 11958115),
        /**Премия по риску только ОСАГО*/
        PREMIUMOSAGO(5,401104),
        /**Премия по риску только ДГО*/
        PREMIUMDGO(6,239349),
        /**Премия по риску только РЕСО-ПОМОЩЬ "ЭКОНОМ"*/
        PREMIUMHELP(7,7),
        /**Премия по риску только РЕСО-ПОМОЩЬ "КОМФОРТ"*/
        PREMIUMHELPCOMFORT(8,11),
        /**Премия по риску только РЕСО-ПОМОЩЬ "СПЕЦ"*/
        PREMIUMHELPSPEC(9,12),
        /**Премия по риску Несчастный случай*/
        PREMIUMACCIDENT(10,254301),
        /**Премия по риску хищение + столкновение*/
        PREMIUMTHFCRASH(11,239348),
        /**Премия по риску  доп. оборудование*/
        PREMIUMEXTEQUIPMENT(12,68331),
        /**Премия по риску Эвакуация*/
        PREMIUMHELPEVACUATION(13,6098596),
        /**Премия по риску */
        PREMIUMHELPEMERGENCY(14,6098592),
        /**Премия по риску Скорая помощь*/
        PREMIUMHELPAMBULANCE(15,6098579),
        /**Премия по риску Аварийный коммисар*/
        HELPAVERAGECOMMISSIONER(16,6098584),
        /**Премия по риску Столкновение*/
        PREMIUMCRASH(17,18715051),
        /**Премия по риску Casco+Rap*/
        PREMIUMCASCORAP(18, -1),
        /** Премия по риску ДГО спец предложение*/
        PREMIUMDGOSPECOFFER(19, 239349),
        /** Премия по риску КАСКО*/
        PREMIUMCASCO(20, -1),
        /**Премия по риску "Эвакуация"*/
        premiumEvacuation(21,239431),
        /**Премия по риску "Ивалидность"*/
        premiumInv(22,254298),
        /**Премия по риску "Стекла"*/
        premiumGls(23,2679882),
        /**Премия по риску "Ущерб Заграница" */
        premiumExtDam(24,6098687),
        /** Премия по риску "Хищение Заграница" */
        premiumExtThf(25,6098694),
        /** Премия по риску "Смерть от несчастного случая  Заграница"*/
        premiumExtDth(26, 6098707),
        /** Премия по риску "Ивалидность Заграница" */
        premiumExtInv(27,6098718),
        /** Премия по риску " ГАП Заграница"*/
        PremiumExtGap(28,15724825);
        
        private final int index;
        private final int riskID;

        private CalcPremium(int index, int riskID) {
            this.index = index;
            this.riskID = riskID;
        }

        public int getIndex() {
            return this.index;
        }
        
        public int getRisk() {
            return this.riskID;
        }
        
        public static CalcPremium getPremiumByRisk(Integer riskId) {
            CalcPremium result = null;

            if (riskId == null) {
                return result;
            }

            for (CalcPremium premium : CalcPremium.values()) {
                if (premium.riskID == riskId) {
                    result = premium;
                    break;
                }
            }

            return result;
        }
    }

}
