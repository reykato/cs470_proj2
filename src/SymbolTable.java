import java.util.HashMap;
import java.util.ArrayList;

public class SymbolTable {
    HashMap<String, Integer> symbol_table;

    ArrayList<Integer> token_type;
    ArrayList<String> token_value;

    public SymbolTable() {
        symbol_table = new HashMap<>();
        token_type = new ArrayList<>();
        token_value = new ArrayList<>();
    }

    public boolean add_symbol(String id, int type, String value) {
        // return true if symbol was added, false if symbol already exists
        if (symbol_table.get(id) == null) {
            symbol_table.put(id, symbol_table.size());
            token_type.add(type);
            token_value.add(value);
            return true;
        } else {
            return false;
        }
    }

    public int get_type(String id) {
        if (get_index(id) != -1) return token_type.get(get_index(id));
        return -1;
    }

    public String get_value(String id) {
        if (get_index(id) != -1) return token_value.get(get_index(id));
        return "VALUE NOT FOUND";
    }

    public int get_index(String id) {
        if (symbol_table.get(id) == null) return -1;
        return symbol_table.get(id);
    }
}
