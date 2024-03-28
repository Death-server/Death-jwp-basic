package next.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private View view;
    public Map<String, Object> jsonData;

    public ModelAndView(View view) {
        this.view = view;
        this.jsonData = new HashMap<>();
    }

    public ModelAndView addObject(String attributeName, Object attributeValue) {
        jsonData.put(attributeName, attributeValue);
        return this;
    }

    public Map<String, Object> getModel() {
        //unmodifiableMap -> Read Only Map
        return Collections.unmodifiableMap(jsonData);
    }

    public View getView() {
        return view;
    }

}
