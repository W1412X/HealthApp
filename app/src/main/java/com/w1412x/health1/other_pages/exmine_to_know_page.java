package com.w1412x.health1.other_pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.w1412x.health1.R;

public class exmine_to_know_page extends AppCompatActivity {
    private WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exmine_to_know_page);
        web=findViewById(R.id.exmine_to_know_page_web);
        web.setWebViewClient(new WebViewClient());
        // HTML 字符串
        String htmlString = "<!DOCTYPE html>\n" +
                "<html lang=\"zh\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>体检须知</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            line-height: 1.6;\n" +
                "            margin: 20px;\n" +
                "        }\n" +
                "        h1, h2, h3 {\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        ul {\n" +
                "            margin: 10px 0;\n" +
                "            padding-left: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>体检须知</h1>\n" +
                "\n" +
                "<h2>一、一般注意事项：</h2>\n" +
                "<ul>\n" +
                "    <li>体检前天22点后禁食，应保持空腹。空腹采血是体检的基本要求，空腹一般是指至少8小时不吃东西。如果早上八点体检，那么体检前一天晚上十点禁食，果汁、奶、咖啡等饮料均不可，但少量饮水（不超过500毫升）不影响体检结果。</li>\n" +
                "    <li>体检前不要大量喝水，做完空腹项目（抽血、腹部超声、呼气试验）后才能吃早餐。虽然白开水不会影响血糖、血脂结果，但喝太多水会促进胆囊排空，影响彩超观察胆囊。</li>\n" +
                "    <li>体检前1-3天，保持清淡饮食，多喝水，不饮酒。油腻的食物会引起身体甘油三酯增高，影响肝功能检查，饮酒会影响肝的转氨酶及血尿酸水平，影响医生判断肝脏的健康状况。</li>\n" +
                "    <li>体检前3天不要剧烈运动。剧烈运动会影响血压和心率，会促进胰岛素、糖皮质激素的释放，还会消耗能量，影响血糖水平。</li>\n" +
                "    <li>超声检查注意事项，做前列腺、膀胱彩超及子宫附件彩超检查，请在检查时保持膀胱充盈(憋尿)。颈部超声检查(包括甲状腺、颈部血管等）不要佩戴项链等饰物。</li>\n" +
                "    <li>体检当日请着宽松轻便服装，不要穿带金属扣的内衣、不要佩戴首饰，女士勿穿连衣裙。</li>\n" +
                "    <li>体检时不要隐瞒病情，医生的问诊和查体可以发现机器无法检出的不良习惯和健康问题。</li>\n" +
                "</ul>\n" +
                "\n" +
                "<h2>二、女性体检特别注意事项：</h2>\n" +
                "<ul>\n" +
                "    <li>应避开月经期，在月经结束3-7天后检查。</li>\n" +
                "    <li>备孕的和怀孕期女性：尽量远离射线，不做DR摄片、CT扫描、骨密度、C14检测等放射性项目。</li>\n" +
                "</ul>\n" +
                "\n" +
                "<h2>三、老年人体检注意事项：</h2>\n" +
                "<ul>\n" +
                "    <li>不要空腹太久，不要晨练，因为体检前不能吃早饭，这种情况下还要坚持晨练，有可能出现低血糖，很危险。</li>\n" +
                "    <li>不要自行停药，高血压、糖尿病、心脏病、哮喘等慢性病患者，体检前不要随意停药，如高血压病人应按时服完降压药后再来体检，糖尿病等其他慢性疾病可携带药物，待做完空腹项目服药。</li>\n" +
                "    <li>做胃肠镜者需停用抗凝药二周。</li>\n" +
                "</ul>\n" +
                "\n" +
                "<h2>四、妇科检查注意事项：</h2>\n" +
                "<ul>\n" +
                "    <li>保持身心松弛，如需官颈癌HPV、TCT筛查，在取样前72小时内要避免行性生活、阴道灌洗、上药等。</li>\n" +
                "    <li>未婚和无性生活史的女性不做妇科检查、妇科腔内彩超等检查。</li>\n" +
                "    <li>不要化浓妆，要穿方便的衣服。化浓妆会影响医生对面部皮肤的判断，隐形眼镜不利于做眼科检查，连衣裙及连体衣不容易穿脱。</li>\n" +
                "    <li>不要自行冲洗阴道。阴道冲洗不仅会增加盆腔炎的可能性，还会影响阴道分泌物常规指标，影响检查结果。</li>\n" +
                "</ul>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
        // 加载 HTML 字符串
        web.loadData(htmlString, "text/html", "UTF-8");
    }
}