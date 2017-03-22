package indi.yume.tools.avocado.string;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.experimental.UtilityClass;

/**
 * Created by yume on 17-3-13.
 */

@UtilityClass
public class FormatUtil {

    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        private List<Text> textList = new LinkedList<>();

        public Builder addText(String s) {
            textList.add(new OriText(s));
            return this;
        }

        public Builder addTexts(String... args) {
            for(String s : args)
                addText(s);
            return this;
        }

        public Builder addText(String format, Object... args) {
            if(args == null || args.length == 0)
                return addText(format);

            List<String> list = new LinkedList<>();
            boolean isAllEmpty = true;
            for(Object ob : args) {
                String s = ob == null ? "" : ob.toString();
                if(!isEmpty(s))
                    isAllEmpty = false;
                list.add(s);
            }

            if(isAllEmpty) {
                textList.add(emptyText);
                return this;
            }

            textList.add(new Group(format, list.toArray(new Object[list.size()])));
            return this;
        }

        public String build() {
            StringBuilder builder = new StringBuilder();
            for (Text text : textList)
                if (!text.isEmpty())
                    builder.append(text.toText());
            return builder.toString();
        }
    }

    private static boolean isEmpty(String string) {
        return string == null || string.length() == 0 || string.trim().length() == 0;
    }

    interface Text {
        String toText();
        boolean isEmpty();
    }

    final static Text emptyText = new Text() {
        @Override
        public String toText() {
            return "";
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    @Data
    static class OriText implements Text {
        private final String string;

        @Override
        public String toText() {
            return string;
        }

        @Override
        public boolean isEmpty() {
            return FormatUtil.isEmpty(string);
        }
    }

    @Data
    static class Group implements Text {
        private final String format;
        private final Object[] args;

        @Override
        public String toText() {
            return String.format(format, args);
        }

        @Override
        public boolean isEmpty() {
            return args == null || args.length == 0;
        }
    }
}
