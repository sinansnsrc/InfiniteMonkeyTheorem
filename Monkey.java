public class Monkey {
    private char[] characters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
    private int shortestWord;
    private int longestWord;
    private String word;

    public Monkey() {
        this.shortestWord = 2;
        this.longestWord = 25;
    }

    public Monkey(int shortestWord, int longestWord) {
        this.shortestWord = shortestWord;
        this.longestWord = longestWord;
    }

    public String type() {
        word = "";

        while(true) {
            char nextLetter = characters[(int) (Math.random() * characters.length)];

            if(nextLetter == ' ' || word.length() >= longestWord) {
                if(word.length() <= shortestWord) {
                    word = "";
                }
                else {
                    return word;
                }
            }

            else {
                word += nextLetter;
            }
        }
    }
}
