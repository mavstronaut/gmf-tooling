/*******************************************************************************
* Copyright (c) 2006 Eclipse.org
* 
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package org.eclipse.gmf.internal.xpand.expression.parser;

public class ExpressionLexerprs implements lpg.lpgjavaruntime.ParseTable, ExpressionLexersym {

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            1,3,3,1,3,1,1,1,1,1,
            2,2,1,1,1,1,1,2,2,2,
            2,1,1,1,1,2,1,1,1,2,
            1,1,1,1,5,1,1,2,3,1,
            2,2,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,2,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,2,2,1,1,1,1,1,
            1,1,1,1,1,1,1,1,3,2,
            2,0,1,2,1,2,0,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,6,2,2,2,2,2,2,2,2,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,17,17,17,14,14,18,
            12,12,12,2,2,2,3,3,3,3,
            3,3,3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,3,3,
            3,3,4,4,4,4,4,4,4,4,
            4,4,4,4,4,4,4,4,4,4,
            4,4,4,4,4,4,4,4,1,1,
            1,1,1,1,1,1,1,1,5,5,
            5,5,5,19,19,8,8,8,8,8,
            8,8,8,8,8,8,8,8,8,8,
            8,8,8,8,8,8,8,8,8,8,
            8,8,8,8,22,22,23,23,23,23,
            23,23,23,23,23,15,15,15,15,20,
            20,20,20,21,21,16,16,13,13,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,9,9,9,
            9,9,9,11,11,11,11,11,11,11,
            11,7,7,7,7,7,7,7,7,7,
            7,7,7,6,6,499,299,298,298,298,
            310,489,201,145,145,145,145,320,299,253,
            145,911,231,6,241,910,114,232,303,311,
            311,311,311,886,38,145,913,311,311,311,
            300,288,897,38,291,303,311,311,311,311,
            914,915,917,301,311,311,311,916,296,909,
            282,291,1,161,161,161,161,161,921,908,
            161,401,166,166,166,166,922,161,490,591,
            166,166,166,304,101,159,159,159,159,159,
            918,912,159,664,262,328,328,328,355,159,
            599,42,41,41,41,328,355,262,729,42,
            41,41,41,794,335,817,337,840,345,335,
            335,337,337,345,345,863,202,355,355,355,
            355,202,202,355,355
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,88,89,
            90,91,92,93,94,95,96,97,98,99,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,88,89,
            90,91,92,93,94,95,96,97,98,99,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,88,89,
            90,91,92,93,94,95,96,97,0,0,
            0,101,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,36,37,
            38,39,40,41,42,43,44,45,46,47,
            48,49,50,51,52,53,54,55,56,57,
            58,59,60,61,62,63,64,65,70,69,
            68,69,70,71,72,73,74,75,76,77,
            78,79,80,81,82,83,84,85,86,87,
            0,0,90,91,92,93,94,95,96,97,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,0,0,68,69,
            70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,0,0,
            90,91,92,93,94,95,96,97,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,29,30,31,
            32,33,34,35,36,37,38,39,40,41,
            42,43,44,45,46,47,48,49,50,51,
            52,53,54,55,56,57,58,59,60,61,
            62,63,64,65,66,67,68,69,70,71,
            72,73,74,75,0,77,78,79,80,81,
            82,83,84,85,86,87,88,89,100,100,
            0,0,0,0,0,0,98,99,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,0,28,29,30,31,
            32,33,34,35,36,37,38,39,40,41,
            42,43,44,45,46,47,48,49,50,51,
            52,53,54,55,56,57,58,59,60,61,
            62,63,64,0,1,2,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            100,28,29,30,31,32,33,34,35,36,
            37,38,39,40,41,42,43,44,45,46,
            47,48,49,50,51,52,53,54,55,56,
            57,58,59,60,61,62,63,64,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,0,28,29,30,31,
            32,33,34,35,36,37,38,39,40,41,
            42,43,44,45,46,47,48,49,50,51,
            52,53,54,55,56,57,58,59,60,61,
            62,63,64,0,1,2,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,0,
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,0,1,2,3,4,
            5,6,7,8,9,10,0,1,2,3,
            4,5,6,7,8,9,10,0,0,0,
            0,0,0,0,0,0,0,0,11,12,
            0,0,0,0,0,0,0,0,0,0,
            23,24,25,26,0,0,27,0,0,27,
            27,27,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,71,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,66,67,65,0,66,67,0,
            0,73,74,76,68,0,66,72,67,0,
            0,0,0,0,0,0,0,0,88,89,
            0,0,0,0,0,0,0,0,98,99,
            0,0,0,0,0,0,0,100,0,0,
            0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            355,516,516,516,516,516,516,516,516,516,
            516,516,516,516,516,516,516,516,516,516,
            516,516,516,516,516,516,516,516,516,516,
            516,516,516,516,516,516,516,516,516,516,
            516,516,516,516,516,516,516,516,516,516,
            516,516,516,516,516,516,516,516,516,516,
            516,516,516,516,516,516,516,516,516,516,
            516,516,516,515,304,516,516,516,516,516,
            516,516,516,516,516,516,516,516,516,516,
            516,516,516,516,516,516,516,516,516,516,
            355,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,390,519,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            514,514,514,514,514,514,514,514,514,514,
            36,500,500,500,500,500,500,500,500,500,
            500,500,500,500,500,500,500,500,500,500,
            500,500,500,500,500,500,500,500,500,500,
            500,500,500,500,500,500,500,500,500,500,
            500,500,500,500,500,500,500,500,500,500,
            500,500,500,500,500,500,500,500,500,500,
            500,500,500,500,500,500,500,500,500,500,
            500,500,500,500,500,500,500,500,500,500,
            500,500,500,500,500,500,500,500,500,500,
            500,500,500,500,500,500,500,500,29,162,
            355,500,167,311,311,311,311,311,311,311,
            311,311,311,311,311,311,311,311,311,311,
            311,311,311,311,311,311,311,311,311,311,
            311,311,311,311,311,311,311,311,311,311,
            311,311,311,311,311,311,311,311,311,311,
            311,311,311,311,311,311,311,311,311,311,
            311,311,311,311,311,311,311,311,385,366,
            311,311,311,311,311,311,311,311,289,311,
            311,311,311,311,311,311,311,311,311,311,
            355,355,311,311,311,311,311,311,311,311,
            168,521,521,521,521,521,521,521,521,521,
            521,521,521,521,521,521,521,521,521,521,
            521,521,521,521,521,521,521,521,521,521,
            521,521,521,521,521,521,521,521,521,521,
            521,521,521,521,521,521,521,521,521,521,
            521,521,521,521,521,521,521,521,521,521,
            521,521,521,521,521,521,355,355,521,521,
            521,521,521,521,521,521,289,521,521,521,
            521,521,521,521,521,521,521,521,355,40,
            521,521,521,521,521,521,521,521,355,299,
            299,299,299,299,299,299,299,299,299,298,
            298,298,298,298,298,298,298,298,298,298,
            298,298,298,298,298,245,298,298,298,298,
            298,298,298,298,298,298,298,298,298,298,
            298,298,298,298,298,298,298,298,298,298,
            298,298,298,298,298,298,298,298,298,298,
            298,298,298,310,248,265,272,273,238,386,
            270,279,364,271,355,379,380,377,378,388,
            383,256,362,277,369,370,310,310,354,1,
            37,355,355,355,355,355,310,310,1,397,
            397,397,397,397,397,397,397,397,397,396,
            396,396,396,396,396,396,396,396,396,396,
            396,396,396,396,396,355,396,396,396,396,
            396,396,396,396,396,396,396,396,396,396,
            396,396,396,396,396,396,396,396,396,396,
            396,396,396,396,396,396,396,396,396,396,
            396,396,396,355,262,262,262,262,262,262,
            262,262,262,262,328,328,328,328,328,328,
            328,328,328,328,328,328,328,328,328,328,
            4,328,328,328,328,328,328,328,328,328,
            328,328,328,328,328,328,328,328,328,328,
            328,328,328,328,328,328,328,328,328,328,
            328,328,328,328,328,328,328,328,5,397,
            397,397,397,397,397,397,397,397,397,396,
            396,396,396,396,396,396,396,396,396,396,
            396,396,396,396,396,355,396,396,396,396,
            396,396,396,396,396,396,396,396,396,396,
            396,396,396,396,396,396,396,396,396,396,
            396,396,396,396,396,396,396,396,396,396,
            396,396,396,355,335,335,335,335,335,335,
            335,335,335,335,335,335,335,335,335,335,
            335,335,335,335,335,335,355,337,337,337,
            337,337,337,337,337,337,337,337,337,337,
            337,337,337,337,337,337,337,337,337,355,
            345,345,345,345,345,345,345,345,345,345,
            345,345,345,345,345,345,345,345,345,345,
            345,345,355,557,557,557,557,557,557,557,
            557,557,557,557,557,557,557,557,557,557,
            557,557,557,557,557,4,393,393,393,393,
            393,393,393,393,393,393,39,393,393,393,
            393,393,393,393,393,393,393,355,10,32,
            34,165,17,16,13,8,27,113,558,561,
            355,355,355,355,355,355,355,355,355,355,
            560,562,559,333,355,355,373,355,355,375,
            374,376,355,355,355,355,355,355,355,355,
            355,355,355,355,355,355,313,355,355,355,
            355,355,355,355,355,355,355,355,355,355,
            355,355,355,563,564,469,355,168,168,355,
            355,499,260,565,367,355,358,381,357,355,
            355,355,355,355,355,355,355,355,469,469,
            355,355,355,355,355,355,355,355,469,469,
            355,355,355,355,355,355,355,34
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int getErrorSymbol() { return 0; }
    public final int getScopeUbound() { return 0; }
    public final int getScopeSize() { return 0; }
    public final int getMaxNameLength() { return 0; }

    public final static int
           NUM_STATES        = 35,
           NT_OFFSET         = 103,
           LA_STATE_OFFSET   = 579,
           MAX_LA            = 1,
           NUM_RULES         = 224,
           NUM_NONTERMINALS  = 24,
           NUM_SYMBOLS       = 127,
           SEGMENT_SIZE      = 8192,
           START_STATE       = 225,
           IDENTIFIER_SYMBOL = 0,
           EOFT_SYMBOL       = 100,
           EOLT_SYMBOL       = 104,
           ACCEPT_ACTION     = 354,
           ERROR_ACTION      = 355;

    public final static boolean BACKTRACK = false;

    public final int getNumStates() { return NUM_STATES; }
    public final int getNtOffset() { return NT_OFFSET; }
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }
    public final int getMaxLa() { return MAX_LA; }
    public final int getNumRules() { return NUM_RULES; }
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }
    public final int getNumSymbols() { return NUM_SYMBOLS; }
    public final int getSegmentSize() { return SEGMENT_SIZE; }
    public final int getStartState() { return START_STATE; }
    public final int getStartSymbol() { return lhs[0]; }
    public final int getIdentifierSymbol() { return IDENTIFIER_SYMBOL; }
    public final int getEoftSymbol() { return EOFT_SYMBOL; }
    public final int getEoltSymbol() { return EOLT_SYMBOL; }
    public final int getAcceptAction() { return ACCEPT_ACTION; }
    public final int getErrorAction() { return ERROR_ACTION; }
    public final boolean isValidForParser() { return isValidForParser; }
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}