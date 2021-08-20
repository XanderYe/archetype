#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.javafx;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 数字输入框
 * @author XanderYe
 * @date 2021/8/20 8:50
 */
public class NumberTextField extends TextField {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?${symbol_escape}${symbol_escape}d+${symbol_escape}${symbol_escape}.?${symbol_escape}${symbol_escape}d*");

    public NumberTextField() {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Matcher matcher = NUMBER_PATTERN.matcher(newValue);
                if (matcher.find()) {
                    this.setText(matcher.group());
                }
            }
        });
    }
}
