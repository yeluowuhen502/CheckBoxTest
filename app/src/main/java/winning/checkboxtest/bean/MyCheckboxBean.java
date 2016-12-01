package winning.checkboxtest.bean;

/**
 * Created by Jiang on 2016/12/1.
 */

public class MyCheckboxBean {
    private String name;
    private boolean byMeChecked;

    public MyCheckboxBean(String name,boolean byMeChecked){
        this.name = name;
        this.byMeChecked = byMeChecked;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isByMeChecked() {
        return byMeChecked;
    }

    public void setByMeChecked(boolean byMeChecked) {
        this.byMeChecked = byMeChecked;
    }
}
