import java.util.*;

/**
 * UseCase3IDEAutocomplete
 * Simulates IDE code completion suggestions (like IntelliJ / VS Code).
 */

public class AutocompleteSystem {

    // code keyword -> frequency
    private HashMap<String, Integer> keywordFrequency = new HashMap<>();

    // update keyword usage frequency
    public void updateFrequency(String keyword) {
        keywordFrequency.put(keyword,
                keywordFrequency.getOrDefault(keyword, 0) + 1);
    }

    // return top 10 suggestions for prefix
    public List<String> suggest(String prefix) {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : keywordFrequency.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                pq.add(entry);
            }
        }

        List<String> suggestions = new ArrayList<>();
        int count = 0;

        while (!pq.isEmpty() && count < 10) {
            Map.Entry<String, Integer> entry = pq.poll();
            suggestions.add(entry.getKey() + " (" + entry.getValue() + " uses)");
            count++;
        }

        return suggestions;
    }

    public static void main(String[] args) {

        AutocompleteSystem  autocomplete = new AutocompleteSystem();

        // simulated IDE keyword usage
        autocomplete.updateFrequency("System.out.println");
        autocomplete.updateFrequency("System.out.print");
        autocomplete.updateFrequency("System.out.println");
        autocomplete.updateFrequency("Scanner");
        autocomplete.updateFrequency("String");
        autocomplete.updateFrequency("StringBuilder");
        autocomplete.updateFrequency("System.exit");

        String prefix = "Sys";

        List<String> results = autocomplete.suggest(prefix);

        System.out.println("IDE Code Suggestions for \"" + prefix + "\":");

        int rank = 1;

        for (String s : results) {
            System.out.println(rank + ". " + s);
            rank++;
        }
    }
}
