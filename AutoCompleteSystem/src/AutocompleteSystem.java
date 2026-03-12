import java.util.*;

/**
 * UseCase1GoogleAutocomplete
 * Simulates Google-like search autocomplete using HashMap and prefix matching.
 */

public class AutocompleteSystem{

    // query -> frequency
    private HashMap<String, Integer> queryFrequency = new HashMap<>();

    // add or update search query
    public void updateFrequency(String query) {
        queryFrequency.put(query, queryFrequency.getOrDefault(query, 0) + 1);
    }

    // return top 10 suggestions for a prefix
    public List<String> search(String prefix) {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : queryFrequency.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                pq.add(entry);
            }
        }

        List<String> results = new ArrayList<>();
        int count = 0;

        while (!pq.isEmpty() && count < 10) {
            Map.Entry<String, Integer> e = pq.poll();
            results.add(e.getKey() + " (" + e.getValue() + " searches)");
            count++;
        }

        return results;
    }

    public static void main(String[] args) {

        AutocompleteSystem autocomplete = new  AutocompleteSystem();

        // Simulated previous search queries
        autocomplete.updateFrequency("java tutorial");
        autocomplete.updateFrequency("javascript");
        autocomplete.updateFrequency("java download");
        autocomplete.updateFrequency("java tutorial");
        autocomplete.updateFrequency("java 21 features");
        autocomplete.updateFrequency("java 21 features");
        autocomplete.updateFrequency("java vs python");
        autocomplete.updateFrequency("javascript tutorial");

        // user types prefix
        String prefix = "jav";

        List<String> suggestions = autocomplete.search(prefix);

        System.out.println("Search suggestions for \"" + prefix + "\":");

        int rank = 1;
        for (String s : suggestions) {
            System.out.println(rank + ". " + s);
            rank++;
        }
    }
}
