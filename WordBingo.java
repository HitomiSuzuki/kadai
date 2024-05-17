import java.util.Scanner;
import java.util.Arrays;

public class WordBingo {
    static int size;
    static int number;
    static String[][] sheet;
    static String[] words;
    static Scanner scanner = new Scanner(System.in);
    
    public static void startGame() {

        WordBingo wb = new WordBingo();

        // ビンゴカードのサイズを取得し、配列sheetをそのサイズで初期化
        size = scanner.nextInt();
        scanner.nextLine(); //数字入力後に改行するために使用
        sheet = new String[size][size];

        // ビンゴカードのサイズに応じて、ビンゴの文字列を取得し、配列sheetに格納
        for(int i = 0; i < size; i++){
            sheet[i] = scanner.nextLine().split("\\s");
        }

        // 与えられる単語数を取得し、配列wordsをその数で初期化
        number = scanner.nextInt();
        scanner.nextLine(); //数字入力後に改行するために使用
        words = new String[number];

        // 配列wordsに与えられた数だけ単語を格納
        for(int i = 0; i < number; i++) {
            words[i] = scanner.next();
        }

        wb.makeHoles();

        if(wb.isRowMatch()) {
            System.out.print("yes");
            return;
        }

        if(wb.isColumnMatch()) {
            System.out.print("yes");
            return;
        }

        if(wb.isDiagonalMatch()) {
            System.out.print("yes");
            return;
        }

        System.out.print("no");
        return;
    }

    // wordsとビンゴカード内の文字列が一致したら、「○」に書きかえる（穴をあける）
    private void makeHoles() {
        for(int i = 0; i < size; i ++) {
            for(int j = 0; j < size; j++) {
                if(Arrays.asList(words).contains(sheet[i][j])) {
                    sheet[i][j] = "○";
                }
            }
        }
    }

    // 横のラインが揃うかどうか
    private boolean isRowMatch() {
        boolean isAnyRowMatch = false;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(sheet[i][j] != "○"){
                    return false;
                }
                isAnyRowMatch = true;
            }
        }
        return isAnyRowMatch;
    }

    // 縦のラインが揃うかどうか
    private boolean isColumnMatch() {
        boolean isAnyColumnMatch = false;
        for(int j = 0; j < size; j ++) {
            for(int i = 0; i < size; i ++) {
                if(sheet[i][j] != "○") {
                    return false;
                }
                isAnyColumnMatch = true;
            }
        }
        return isAnyColumnMatch;
    } 

    // 斜めのラインが揃うかどうか
    private boolean isDiagonalMatch() {
        boolean isRightDownDiagonalMatch = true;
        boolean isLeftDownDiagonalMatch = true;
        int maxIndex = size - 1;

        // 左上から右下への斜めライン
        for(int i = 0; i < size; i++) {
            if(sheet[i][i] != "○") {
                isRightDownDiagonalMatch = false;
            }  
        }
        if(isRightDownDiagonalMatch) {
            return true;
        }

        // 右上から左下への斜めライン
        for(int i = 0; i < size; i++) {
            if(sheet[i][maxIndex - i] != "○") {
                isLeftDownDiagonalMatch = false;
            }
        }
        if(isLeftDownDiagonalMatch) {
            return true;
        }

        return false;
    }
}
