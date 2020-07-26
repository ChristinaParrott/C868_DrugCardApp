package com.example.drugcardapp.util;

import com.example.drugcardapp.database.DrugCardEntity;
import com.example.drugcardapp.database.QuizEntity;

import java.util.ArrayList;
import java.util.List;

public class SampleCards {
    private static final String DRUG_NAME_1 = "metoprolol";
    private static final String DRUG_TRADE_1 = "Lopressor";
    private static final String DRUG_CLASS_1 = "Beta Blocker";
    private static final String DRUG_SYSTEM_1 = "Cardiovascular";
    private static final String DRUG_ACTION_1 = "Blocks beta-adrenergic receptors";
    private static final String DRUG_SE_1 = "fatigue, weakness, bradycardia";
    private static final String DRUG_ADVERSE_1 = "fatigue, weakness, bradycardia";
    private static final String DRUG_INT_1 = "ETOH, antihypertensives, insulin, synthroid";
    private static final String DRUG_IMP_1 = "Apical pulse <50 hold";
    private static final String DRUG_OTHER_1 = "n/a";

    private static final String DRUG_NAME_2 = "lisinopril";
    private static final String DRUG_TRADE_2 = "Zestril";
    private static final String DRUG_CLASS_2 = "Angiotensin-Converting Enzyme Inhibitor";
    private static final String DRUG_SYSTEM_2 = "Cardiovascular";
    private static final String DRUG_ACTION_2 = "Blocks conversion of angiotensin I to angiotensin II";
    private static final String DRUG_SE_2 = "fatigue, weakness, bradycardia";
    private static final String DRUG_ADVERSE_2 = "Angioedema";
    private static final String DRUG_INT_2 = "NSAIDs, antihypertensives, diuretics, potassium supplements";
    private static final String DRUG_IMP_2 = "Persistent dry cough";
    private static final String DRUG_OTHER_2 = "n/a";

    private static final String DRUG_NAME_3 = "amlodipine";
    private static final String DRUG_TRADE_3 = "Norvasc";
    private static final String DRUG_CLASS_3 = "Calcium Channel Blocker";
    private static final String DRUG_SYSTEM_3 = "Cardiovascular";
    private static final String DRUG_ACTION_3 = "Inhibits the transport of calcium into myocardial and vascular smooth muscle cells";
    private static final String DRUG_SE_3 = "peripheral edema, fatigue, weakness, bradycardia";
    private static final String DRUG_ADVERSE_3 = "n/a";
    private static final String DRUG_INT_3 = "grapefruit juice increases serum levels";
    private static final String DRUG_IMP_3 = "Orthostatic hypotension, avoid grapefruit juice";
    private static final String DRUG_OTHER_3 = "n/a";

    private static final String DRUG_NAME_4 = "levothyroxine";
    private static final String DRUG_TRADE_4 = "Synthroid";
    private static final String DRUG_CLASS_4 = "Thyroid Preparations";
    private static final String DRUG_SYSTEM_4 = "Endocrine";
    private static final String DRUG_ACTION_4 = "Supplementation of thyroid hormones. Increases metabolic rate.";
    private static final String DRUG_SE_4 = "tachycardia, abd cramps, headache, insomnia";
    private static final String DRUG_ADVERSE_4 = "n/a";
    private static final String DRUG_INT_4 = "bile acid sequestrants, warfarin, andrenergics";
    private static final String DRUG_IMP_4 = "Take with full glass of H20 before breakfast";
    private static final String DRUG_OTHER_4 = "n/a";

    private static final String DRUG_NAME_5 = "atorvastatin";
    private static final String DRUG_TRADE_5 = "Lipitor";
    private static final String DRUG_CLASS_5 = "HMG-CoA Reductase Inhibitors";
    private static final String DRUG_SYSTEM_5 = "Hematologic";
    private static final String DRUG_ACTION_5 = "Inhibits HMG-CoA reductase, an enzyme that catalyzes the synthesis of cholesterol.";
    private static final String DRUG_SE_5 = "abd cramps, constipation, dirrahea, flatus, heartburn, rashes";
    private static final String DRUG_ADVERSE_5 = "rhabdomylosis, angioneurotic edema";
    private static final String DRUG_INT_5 = "bile acid sequestrants, antibiotics, antivirals, grapefruit juice, warfarin";
    private static final String DRUG_IMP_5 = "Notify HCP of muscle pain, tenderness, or weakness, monitor CPK";
    private static final String DRUG_OTHER_5 = "Expect decrease in LDL and total Cholesterol, increase in HDL";

    private static final String DRUG_NAME_6 = "metformin";
    private static final String DRUG_TRADE_6 = "Glucophage";
    private static final String DRUG_CLASS_6 = "Biguanides";
    private static final String DRUG_SYSTEM_6 = "Endocrine";
    private static final String DRUG_ACTION_6 = "Decreases hepatic glucose production. Decreases intestinal glucose absorption. Increases insulin sensitivity.";
    private static final String DRUG_SE_6 = "abd bloating, dirrahea, N&V, hypoglycemia";
    private static final String DRUG_ADVERSE_6 = "lactic acidosis";
    private static final String DRUG_INT_6 = "ETOH, iodinated contrast media";
    private static final String DRUG_IMP_6 = "Hold if contrast media must be used due to risk for lactic acidosis. Monitor for renal dysfunction";
    private static final String DRUG_OTHER_6 = "Instruct pt to monitor blood glucose for hyper/hypogylcemia";

    private static final String DRUG_NAME_7 = "omeprazole";
    private static final String DRUG_TRADE_7 = "Prilosec";
    private static final String DRUG_CLASS_7 = "Proton Pump Inhibitor";
    private static final String DRUG_SYSTEM_7 = "Gastrointestinal";
    private static final String DRUG_ACTION_7 = "Binds to gastric parietal cells in the presence of acidic gastric pH to prevent H ions from being transported to gastric lumen.";
    private static final String DRUG_SE_7 = "abdominal pain, hypomagnesemia (in prolonged use)";
    private static final String DRUG_ADVERSE_7 = "pseudomembranous colitis";
    private static final String DRUG_INT_7 = "antifungal agents, St. John's wort";
    private static final String DRUG_IMP_7 = "Monitor bowel function for S&S of pseudomembranous colitis (diarrhea, abd cramping, fever, bloody stools)";
    private static final String DRUG_OTHER_7 = "n/a";

    private static final String DRUG_NAME_8 = "simvastatin";
    private static final String DRUG_TRADE_8 = "Zocor";
    private static final String DRUG_CLASS_8 = "HMG-CoA Reductase Inhibitors";
    private static final String DRUG_SYSTEM_8 = "Hematologic";
    private static final String DRUG_ACTION_8 = "Inhibits HMG-CoA reductase, an enzyme that catalyzes the synthesis of cholesterol.";
    private static final String DRUG_SE_8 = "abd cramps, constipation, dirrahea, flatus, heartburn, rashes";
    private static final String DRUG_ADVERSE_8 = "rhabdomylosis, angioneurotic edema";
    private static final String DRUG_INT_8 = "bile acid sequestrants, antibiotics, antivirals, grapefruit juice, warfarin";
    private static final String DRUG_IMP_8 = "Notify HCP of muscle pain, tenderness, or weakness, monitor CPK";
    private static final String DRUG_OTHER_8 = "Expect decrease in LDL and total Cholesterol, increase in HDL";

    private static final String DRUG_NAME_9 = "losartan";
    private static final String DRUG_TRADE_9 = "Cozaar";
    private static final String DRUG_CLASS_9 = "Angiotensin II Receptor Blocker";
    private static final String DRUG_SYSTEM_9 = "Cardiovascular";
    private static final String DRUG_ACTION_9 = "Blocks the vasconstrictor and aldosterone secreting effects of angiotensin II at vascular smooth muscle and adrenal gland receptor sites.";
    private static final String DRUG_SE_9 = "diarrhea, dizziness, fatigue, hyperkalemia, myalgia";
    private static final String DRUG_ADVERSE_9 = "angioedema";
    private static final String DRUG_INT_9 = "potassium supplements, salt substitutes, potassium-sparing diuretics, NSAIDs, COX-2 Inhibitors";
    private static final String DRUG_IMP_9 = "Assess for S&S of angioedema (dyspnea, facial swelling), monitor renal function, orthostatic hypotension.";
    private static final String DRUG_OTHER_9 = "n/a";

    private static final String DRUG_NAME_10 = "albuterol";
    private static final String DRUG_TRADE_10 = "Ventolin";
    private static final String DRUG_CLASS_10 = "Bronchodilator";
    private static final String DRUG_SYSTEM_10 = "Respiratory";
    private static final String DRUG_ACTION_10 = "Binds to B-adrenergic receptors in airway smooth muscle, leading to relaxation and bronchodilation";
    private static final String DRUG_SE_10 = "nervousness, restlessness, tremor, chest pain, palpitations";
    private static final String DRUG_ADVERSE_10 = "paradoxical bronchopasms (excessive use)";
    private static final String DRUG_INT_10 = "Concurrent use with other adrenergic agents.";
    private static final String DRUG_IMP_10 = "Monitor for bronchospasm, especially after first dose from new canister. Use albuterol before a longer acting agent.";
    private static final String DRUG_OTHER_10 = "n/a";

    private static final String DRUG_NAME_11 = "gabapentin";
    private static final String DRUG_TRADE_11 = "Neurontin";
    private static final String DRUG_CLASS_11 = "Anticonvulsant";
    private static final String DRUG_SYSTEM_11 = "Neurological";
    private static final String DRUG_ACTION_11 = "Unknown mechanism. Decreases incidence of seizures, relieves neuropathic pain, prevents migraine headache, treatment of bipolar disorder and anxiety.";
    private static final String DRUG_SE_11 = "confusion, depression, dizziness, drowsiness, ataxia";
    private static final String DRUG_ADVERSE_11 = "suicidal thoughts, multi-organ hypersensitivity";
    private static final String DRUG_INT_11 = "antacids, CNS depressants, valerian";
    private static final String DRUG_IMP_11 = "Monitor for thoughts of suicide, trouble sleeping, irritability,  impulsive behavior. Don't administer within 2 hr of antacid.";
    private static final String DRUG_OTHER_11 = "n/a";
    public static List<DrugCardEntity> getCards(){
        List<DrugCardEntity> cards = new ArrayList<>();
        cards.add(new DrugCardEntity(DRUG_NAME_1, DRUG_TRADE_1,DRUG_CLASS_1,DRUG_SYSTEM_1, DRUG_ACTION_1,
                DRUG_SE_1,DRUG_ADVERSE_1,DRUG_INT_1,DRUG_IMP_1, DRUG_OTHER_1));
        cards.add(new DrugCardEntity(DRUG_NAME_2, DRUG_TRADE_2,DRUG_CLASS_2,DRUG_SYSTEM_2, DRUG_ACTION_2,
                DRUG_SE_2,DRUG_ADVERSE_2,DRUG_INT_2,DRUG_IMP_2, DRUG_OTHER_2));
        cards.add(new DrugCardEntity(DRUG_NAME_3, DRUG_TRADE_3,DRUG_CLASS_3,DRUG_SYSTEM_3, DRUG_ACTION_3,
                DRUG_SE_3,DRUG_ADVERSE_3,DRUG_INT_3,DRUG_IMP_3, DRUG_OTHER_3));
        cards.add(new DrugCardEntity(DRUG_NAME_4, DRUG_TRADE_4,DRUG_CLASS_4,DRUG_SYSTEM_4, DRUG_ACTION_4,
                DRUG_SE_4,DRUG_ADVERSE_4,DRUG_INT_4,DRUG_IMP_4, DRUG_OTHER_4));
        cards.add(new DrugCardEntity(DRUG_NAME_5, DRUG_TRADE_5,DRUG_CLASS_5,DRUG_SYSTEM_5, DRUG_ACTION_5,
                DRUG_SE_5,DRUG_ADVERSE_5,DRUG_INT_5,DRUG_IMP_5, DRUG_OTHER_5));
        cards.add(new DrugCardEntity(DRUG_NAME_6, DRUG_TRADE_6,DRUG_CLASS_6,DRUG_SYSTEM_6, DRUG_ACTION_6,
                DRUG_SE_6,DRUG_ADVERSE_6,DRUG_INT_6,DRUG_IMP_6, DRUG_OTHER_6));
        cards.add(new DrugCardEntity(DRUG_NAME_7, DRUG_TRADE_7,DRUG_CLASS_7,DRUG_SYSTEM_7, DRUG_ACTION_7,
                DRUG_SE_7,DRUG_ADVERSE_7,DRUG_INT_7,DRUG_IMP_7, DRUG_OTHER_7));
        cards.add(new DrugCardEntity(DRUG_NAME_8, DRUG_TRADE_8,DRUG_CLASS_8,DRUG_SYSTEM_8, DRUG_ACTION_8,
                DRUG_SE_8,DRUG_ADVERSE_8,DRUG_INT_8,DRUG_IMP_8, DRUG_OTHER_8));
        cards.add(new DrugCardEntity(DRUG_NAME_9, DRUG_TRADE_9,DRUG_CLASS_9,DRUG_SYSTEM_9, DRUG_ACTION_9,
                DRUG_SE_9,DRUG_ADVERSE_9,DRUG_INT_9,DRUG_IMP_9, DRUG_OTHER_9));
        cards.add(new DrugCardEntity(DRUG_NAME_10, DRUG_TRADE_10,DRUG_CLASS_10,DRUG_SYSTEM_10, DRUG_ACTION_10,
                DRUG_SE_10,DRUG_ADVERSE_10,DRUG_INT_10,DRUG_IMP_10, DRUG_OTHER_10));
        cards.add(new DrugCardEntity(DRUG_NAME_11, DRUG_TRADE_11,DRUG_CLASS_11,DRUG_SYSTEM_11, DRUG_ACTION_11,
                DRUG_SE_11,DRUG_ADVERSE_11,DRUG_INT_11,DRUG_IMP_11, DRUG_OTHER_11));
        return cards;
    }
    public static List<QuizEntity> getQuizzes(){
       List<QuizEntity> quizzes = new ArrayList<>();
       ArrayList<DrugCardEntity> CVcards = new ArrayList<>();
        CVcards.add(new DrugCardEntity(DRUG_NAME_1, DRUG_TRADE_1,DRUG_CLASS_1,DRUG_SYSTEM_1, DRUG_ACTION_1,
                DRUG_SE_1,DRUG_ADVERSE_1,DRUG_INT_1,DRUG_IMP_1, DRUG_OTHER_1));
        CVcards.add(new DrugCardEntity(DRUG_NAME_2, DRUG_TRADE_2,DRUG_CLASS_2,DRUG_SYSTEM_2, DRUG_ACTION_2,
                DRUG_SE_2,DRUG_ADVERSE_2,DRUG_INT_2,DRUG_IMP_2, DRUG_OTHER_2));
        CVcards.add(new DrugCardEntity(DRUG_NAME_3, DRUG_TRADE_3,DRUG_CLASS_3,DRUG_SYSTEM_3, DRUG_ACTION_3,
                DRUG_SE_3,DRUG_ADVERSE_3,DRUG_INT_3,DRUG_IMP_3, DRUG_OTHER_3));
        CVcards.add(new DrugCardEntity(DRUG_NAME_5, DRUG_TRADE_5,DRUG_CLASS_5,DRUG_SYSTEM_5, DRUG_ACTION_5,
                DRUG_SE_5,DRUG_ADVERSE_5,DRUG_INT_5,DRUG_IMP_5, DRUG_OTHER_5));
        CVcards.add(new DrugCardEntity(DRUG_NAME_8, DRUG_TRADE_8,DRUG_CLASS_8,DRUG_SYSTEM_8, DRUG_ACTION_8,
                DRUG_SE_8,DRUG_ADVERSE_8,DRUG_INT_8,DRUG_IMP_8, DRUG_OTHER_8));
        CVcards.add(new DrugCardEntity(DRUG_NAME_9, DRUG_TRADE_9,DRUG_CLASS_9,DRUG_SYSTEM_9, DRUG_ACTION_9,
                DRUG_SE_9,DRUG_ADVERSE_9,DRUG_INT_9,DRUG_IMP_9, DRUG_OTHER_9));
        ArrayList<DrugCardEntity> Endocards = new ArrayList<>();
        Endocards.add(new DrugCardEntity(DRUG_NAME_4, DRUG_TRADE_4,DRUG_CLASS_4,DRUG_SYSTEM_4, DRUG_ACTION_4,
                DRUG_SE_4,DRUG_ADVERSE_4,DRUG_INT_4,DRUG_IMP_4, DRUG_OTHER_4));
        Endocards.add(new DrugCardEntity(DRUG_NAME_6, DRUG_TRADE_6,DRUG_CLASS_6,DRUG_SYSTEM_6, DRUG_ACTION_6,
                DRUG_SE_6,DRUG_ADVERSE_6,DRUG_INT_6,DRUG_IMP_6, DRUG_OTHER_6));
        ArrayList<DrugCardEntity> GIcards = new ArrayList<>();
        GIcards.add(new DrugCardEntity(DRUG_NAME_7, DRUG_TRADE_7,DRUG_CLASS_7,DRUG_SYSTEM_7, DRUG_ACTION_7,
                DRUG_SE_7,DRUG_ADVERSE_7,DRUG_INT_7,DRUG_IMP_7, DRUG_OTHER_7));
        ArrayList<DrugCardEntity> Respcards = new ArrayList<>();
        Respcards.add(new DrugCardEntity(DRUG_NAME_10, DRUG_TRADE_10,DRUG_CLASS_10,DRUG_SYSTEM_10, DRUG_ACTION_10,
                DRUG_SE_10,DRUG_ADVERSE_10,DRUG_INT_10,DRUG_IMP_10, DRUG_OTHER_10));
        ArrayList<DrugCardEntity> Paincards = new ArrayList<>();
        Paincards.add(new DrugCardEntity(DRUG_NAME_11, DRUG_TRADE_11,DRUG_CLASS_11,DRUG_SYSTEM_11, DRUG_ACTION_11,
                DRUG_SE_11,DRUG_ADVERSE_11,DRUG_INT_11,DRUG_IMP_11, DRUG_OTHER_11));
        QuizEntity CVquiz = new QuizEntity("Cardiovascular Quiz", CVcards);
        QuizEntity Endoquiz = new QuizEntity("Endocrine Quiz", Endocards);
        QuizEntity GIquiz = new QuizEntity("Gastrointestinal Quiz", GIcards);
        QuizEntity Respquiz = new QuizEntity("Respiratory Quiz", Respcards);
        QuizEntity Painquiz = new QuizEntity("Pain Quiz", Paincards);
        quizzes.add(CVquiz);
        quizzes.add(Endoquiz);
        quizzes.add(GIquiz);
        quizzes.add(Respquiz);
        quizzes.add(Painquiz);
       return quizzes;
    }
}
