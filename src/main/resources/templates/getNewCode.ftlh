<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en-US" class="supernova">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta property="og:title" content="Code">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=1"/>
    <meta name="HandheldFriendly" content="true"/>
    <title>Create</title>
    <style type="text/css">@media print {
            .form-section {
                display: inline !important
            }

            .form-pagebreak {
                display: none !important
            }

            .form-section-closed {
                height: auto !important
            }

            .page-section {
                position: initial !important
            }
        }</style>
    <link type="text/css" rel="stylesheet"
          href="https://cdn01.jotfor.ms/themes/CSS/5e6b428acc8c4e222d1beb91.css?themeRevisionID=5f7ed99c2c2c7240ba580251"/>
    <link type="text/css" rel="stylesheet"
          href="https://cdn02.jotfor.ms/css/styles/payment/payment_styles.css?3.3.33217"/>
    <link type="text/css" rel="stylesheet"
          href="https://cdn03.jotfor.ms/css/styles/payment/payment_feature.css?3.3.33217"/>
    <script src="https://cdn01.jotfor.ms/static/prototype.forms.js" type="text/javascript"></script>
    <script src="https://cdn02.jotfor.ms/static/jotform.forms.js?3.3.33217" type="text/javascript"></script>
    <script>
        function send() {
            let object = {
                "code": document.getElementById("code_snippet").value
            };
            let json = JSON.stringify(object);
            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false)
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);
            if (xhr.status == 200) {
                alert("Success!");
            }
        }
    </script>
</head>
<body>
<form class="jotform-form" action="/" method="post"
      name="form_221341704062342" id="221341704062342" accept-charset="utf-8" autocomplete="on">
    <input type="hidden" name="formID" value="221341704062342"/>
    <input type="hidden" id="JWTContainer" value=""/>
    <input type="hidden" id="cardinalOrderNumber" value=""/>
    <div role="main" class="form-all">
        <style>
            .form-all:before {
                background: none;
            }
        </style>
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
            <li class="form-line" data-type="control_textarea" id="id_16">
                <label class="form-label form-label-top" id="label_16" for="code_snippet"> </label>
                <div id="cid_16" class="form-input-wide" data-layout="full">
                            <textarea id="code_snippet" class="form-textarea custom-hint-group form-custom-hint"
                                      name="q16_yourCode"
                                      style="width:648px;height:163px" data-component="textarea"
                                      aria-labelledby="label_16"
                                      data-customhint="// write your code here" customhinted="true"
                                      placeholder="// write your code here" spellcheck="false"></textarea>
                </div>
            </li>
            <li class="form-line" data-type="control_button" id="id_26">
                <div id="cid_26" class="form-input-wide" data-layout="full">
                    <div data-align="auto" class="form-buttons-wrapper form-buttons-auto   jsTest-button-wrapperField">
                        <button id="send_snippet" type="submit"
                                class="form-submit-button submit-button jf-form-buttons jsTest-submitField"
                                data-component="button" data-content="" onclick="send()">
                            Submit
                        </button>
                    </div>
                </div>
            </li>
            <li style="display:none">
                Should be Empty:
                <input type="text" name="website" value=""/>
            </li>
        </ul>
    </div>
</form>
</body>
</html>