<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link type="text/css" rel="stylesheet"
          href="https://cdn01.jotfor.ms/themes/CSS/5e6b428acc8c4e222d1beb91.css?themeRevisionID=5f7ed99c2c2c7240ba580251"/>
    <link href="resources/static/getCodeStyle.css" rel="stylesheet"/>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/atom-one-light.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <link type="text/css" rel="stylesheet"
          href="https://cdn02.jotfor.ms/css/styles/payment/payment_styles.css?3.3.33217"/>
    <link type="text/css" rel="stylesheet"
          href="https://cdn03.jotfor.ms/css/styles/payment/payment_feature.css?3.3.33217"/>
    <script src="https://cdn01.jotfor.ms/static/prototype.forms.js" type="text/javascript"></script>
    <script src="https://cdn02.jotfor.ms/static/jotform.forms.js?3.3.33217" type="text/javascript"></script>
</head>
<body>
<style type="text/css">
    #load_date {
        color: lightcoral;
        font-size: 14px;
    }

    #code_snippet {
        border-top: 1px solid;
        border-bottom: 1px solid;
        border-left: 1px solid;
        border-right: 1px solid;
        background: ghostwhite;
    }
</style>
<div role="main" class="form-all">
    <ul class="form-section page-section">
        <li id="cid_1" class="form-input-wide" data-type="control_head">
            <div class="form-header-group  header-large">
                <div class="header-text httac htvam">
                    <h1 id="header_1" class="form-header" data-component="header">
                        Code Sharing Platform
                    </h1>
                </div>
            </div>
        </li>

        <div id="oneCode">
            <span id="load_date">${code.date}</span>
            <#if code.viewsRestricted == true>
                <p><b><span id="views_restriction">${code.views}</span></b> more views allowed</p>
            </#if>
            <#if code.timeRestricted == true>
                <p>The code will be available for <b><span id="time_restriction">${code.time}</span></b> seconds</p>
            </#if>
            <pre id="code_snippet" align="left">
                <code>${code.code}</code>
            </pre>
        </div>

    </ul>
</div>
</body>
</html>