package indi.yume.tools.sample;

import com.googlecode.totallylazy.Function3;
import com.googlecode.totallylazy.Streams;
import com.googlecode.totallylazy.Strings;
import com.googlecode.totallylazy.json.Json;
import com.googlecode.totallylazy.numbers.Integers;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import fj.F;
import fj.F1Functions;
import fj.F1W;
import fj.LcgRng;
import fj.P2;
import fj.Rng;
import fj.Unit;
import fj.data.IO;
import fj.data.IOFunctions;
import fj.data.IOW;
import fj.data.Stream;

import static com.googlecode.totallylazy.Functions.uncurry3;
import static com.googlecode.totallylazy.numbers.Numbers.add;
import static com.googlecode.totallylazy.numbers.Numbers.multiply;
import static fj.data.IOFunctions.toSafeValidation;

/**
 * Created by yume on 16-5-31.
 */

public class FunctionJavaTest {
    static final String JSON = "[{\"name\":\"笔记本（广博）\",\"barCode\":\"6922711027944\",\"salePrice\":3.05,\"costPrice\":7.0,\"count\":615,\"unit\":\"本\",\"packageNum\":2431120,\"note\":\"I\",\"className\":\"办公用品\"},{\"name\":\"葡萄汁\",\"barCode\":\"6932571040168\",\"salePrice\":4.5,\"costPrice\":5.0,\"count\":6,\"unit\":\"瓶\",\"packageNum\":1,\"note\":\"可以喝\",\"className\":\"饮料\"},{\"name\":\"红枣\",\"barCode\":\"6957261401081\",\"salePrice\":2.0,\"costPrice\":1.0,\"count\":15,\"unit\":\"袋\",\"packageNum\":1,\"note\":\"尝尝？\",\"className\":\"果脯\"},{\"name\":\"Git权威指南\",\"barCode\":\"9787111349679\",\"salePrice\":89.0,\"costPrice\":50.0,\"count\":105,\"unit\":\"本\",\"packageNum\":1,\"note\":\"l\",\"className\":\"书\"},{\"name\":\"维达抽纸\",\"barCode\":\"6901236341308\",\"salePrice\":3.0,\"costPrice\":2.0,\"count\":144,\"unit\":\"抽\",\"packageNum\":1,\"note\":\"超柔细密，肌肤般的触感，湿水不易破，单身必备\",\"className\":\"日用品\"},{\"name\":\"光明 纯牛奶\",\"barCode\":\"6901209302220\",\"salePrice\":1.0,\"costPrice\":2.5,\"count\":62,\"unit\":\"盒\",\"packageNum\":1,\"note\":\"好喝的牛奶，补偿每天所需蛋白质，每天限量一盒喔\",\"className\":\"饮料\"},{\"name\":\"牛角包（芝士味）\",\"barCode\":\"6911988018809\",\"salePrice\":0.5,\"costPrice\":1.0,\"count\":11,\"unit\":\"包\",\"packageNum\":1,\"note\":\"层层酥软，层层浪漫～\",\"className\":\"食物\"},{\"name\":\"法式小面包（香奶味）\",\"barCode\":\"6911988011633\",\"salePrice\":0.5,\"costPrice\":1.0,\"count\":1,\"unit\":\"包\",\"packageNum\":1,\"note\":\"好吃的小面包，牛奶好伴侣\",\"className\":\"食物\"},{\"name\":\"脆脆鲨（抹茶味）\",\"barCode\":\"6917878029917\",\"salePrice\":0.5,\"costPrice\":1.0,\"count\":10,\"unit\":\"条\",\"packageNum\":1,\"note\":\"甜而不腻，能量108大卡\",\"className\":\"零食\"},{\"name\":\"脆脆鲨（巧克力味）\",\"barCode\":\"6917878131504\",\"salePrice\":0.5,\"costPrice\":1.0,\"count\":10,\"unit\":\"条\",\"packageNum\":1,\"note\":\"脆香好滋味，能量103大卡\",\"className\":\"零食\"},{\"name\":\"脆脆鲨（黑芝麻味）\",\"barCode\":\"6917878020570\",\"salePrice\":0.5,\"costPrice\":1.0,\"count\":0,\"unit\":\"条\",\"packageNum\":1,\"note\":\"脆香好滋味，能量105大卡\",\"className\":\"零食\"},{\"name\":\"乐事薯片（美国经典原味）\",\"barCode\":\"6924743915398\",\"salePrice\":3.0,\"costPrice\":7.9,\"count\":0,\"unit\":\"袋\",\"packageNum\":1,\"note\":\"经典原味，好吃停不下来！能量242大卡\",\"className\":\"零食\"},{\"name\":\"乐事薯片（意大利香浓红烩味）\",\"barCode\":\"6924743915411\",\"salePrice\":3.0,\"costPrice\":7.9,\"count\":0,\"unit\":\"袋\",\"packageNum\":1,\"note\":\"好吃停不下来，能量236大卡\",\"className\":\"零食\"},{\"name\":\"芬达 橙味汽水\",\"barCode\":\"6953392520011\",\"salePrice\":1.0,\"costPrice\":2.5,\"count\":20,\"unit\":\"罐\",\"packageNum\":1,\"note\":\"酸酸甜甜，夏天的味道～ 能量146大卡\",\"className\":\"饮料\"},{\"name\":\"PARIS\",\"barCode\":\"6951951480134\",\"salePrice\":10000.0,\"costPrice\":1.0E8,\"count\":1,\"unit\":\"盒\",\"packageNum\":1,\"note\":\"Glaude Galien\",\"className\":\"化妆品\"},{\"name\":\"零度可乐\",\"barCode\":\"6953392500013\",\"salePrice\":1.0,\"costPrice\":2.04,\"count\":23,\"unit\":\"罐\",\"packageNum\":1,\"note\":\"无糖可乐，能量ZERO\",\"className\":\"饮料\"},{\"name\":\"PARIS\",\"barCode\":\"6951951480134\",\"salePrice\":10000.0,\"costPrice\":1000000.0,\"count\":1,\"unit\":\"盒\",\"packageNum\":1,\"note\":\"Glaude Galien\",\"className\":\"化妆品\"},{\"name\":\"PARIS\",\"barCode\":\"6951951480134\",\"salePrice\":10000.0,\"costPrice\":1000000.0,\"count\":1,\"unit\":\"盒\",\"packageNum\":1,\"note\":\"Glaude Galien\",\"className\":\"化妆品\"},{\"name\":\"PARIS\",\"barCode\":\"6951951480134\",\"salePrice\":10000.0,\"costPrice\":1000000.0,\"count\":1,\"unit\":\"盒\",\"packageNum\":1,\"note\":\"Glaude Galien\",\"className\":\"化妆品\"},{\"name\":\"PARIS\",\"barCode\":\"6951951480134\",\"salePrice\":10000.0,\"costPrice\":1000000.0,\"count\":1,\"unit\":\"盒\",\"packageNum\":1,\"note\":\"Glaude Galien\",\"className\":\"化妆品\"},{\"name\":\"PARIS\",\"barCode\":\"6951951480134\",\"salePrice\":10000.0,\"costPrice\":1000000.0,\"count\":1,\"unit\":\"盒\",\"packageNum\":1,\"note\":\"Glaude Galien\",\"className\":\"化妆品\"},{\"name\":\"PARIS\",\"barCode\":\"6951951480134\",\"salePrice\":10000.0,\"costPrice\":1000000.0,\"count\":1,\"unit\":\"盒\",\"packageNum\":1,\"note\":\"Glaude Galien\",\"className\":\"化妆品\"},{\"name\":\"PARIS\",\"barCode\":\"6951951480134\",\"salePrice\":10000.0,\"costPrice\":1000000.0,\"count\":1,\"unit\":\"盒\",\"packageNum\":1,\"note\":\"Glaude Galien\",\"className\":\"化妆品\"}]";

    @Test
    public void testFun1() {
        List<String> list = Json.list(JSON);
        System.out.println(list);
        System.out.println(Integers.functions.add(10).deferApply(2).map(multiply(3)).apply());
        System.out.println(addThenMultiple().apply(10).apply(2).apply(3));
        Rng rng = new LcgRng();

        for(int i = 0; i < 20; i++) {
            P2<Rng, Integer> p2 = rng.nextInt();
            System.out.println(p2._2());
            rng = p2._1();
        }
    }

    public void testIOW() throws IOException {
        final IO<Unit> askName = () -> {
            System.out.println("Hi, what's your name?");
            return Unit.unit();
        };

        IO<Unit> promptName = IOFunctions.stdoutPrintln("Name: ");

        IO<Unit> askAndPromptName = IOFunctions.append(askName, promptName);

        final IO<String> readName = () -> new BufferedReader(new InputStreamReader(System.in)).readLine();

        final F<String, IO<Unit>> upperCaseAndPrint = F1Functions.<String, IO<Unit>, String>o(IOFunctions::stdoutPrintln).f(String::toUpperCase);

        final IO<Unit> readAndPrintUpperCasedName = IOFunctions.bind(readName, upperCaseAndPrint);

        final IO<Unit> program = IOFunctions.bind(askAndPromptName, ignored -> readAndPrintUpperCasedName);

        toSafeValidation(program).run().on((IOException e) -> { e.printStackTrace(); return Unit.unit(); });

//        IOFunctions.toSafe(IOW.lift(IOFunctions.stdoutPrintln("What's your name again?"))
//                .append(IOFunctions.stdoutPrintln("Name: "))
//                .append(IOFunctions.stdinReadLine())
//                .bind(F1W.lift((String s) -> s.toUpperCase()).andThen(IOFunctions::stdoutPrintln)))
//                .run()
//                .on((IOException e) -> { e.printStackTrace(); return Unit.unit(); });
    }

    private Function3<Number, Number, Number, Number> addThenMultiple() {
        return uncurry3(add().then(multiply()));
    }
}
