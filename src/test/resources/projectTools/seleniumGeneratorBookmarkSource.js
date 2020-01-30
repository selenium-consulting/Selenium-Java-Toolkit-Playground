javascript:{
    function offset(elt) {
        var rect = elt.getBoundingClientRect();
        return {
            top: rect.top + document.documentElement.scrollTop,
            left: rect.left + document.documentElement.scrollLeft
        }
    }

    var selector = '[data-seleniumid]';
    var seleniumDiv = document.createElement('div');
    seleniumDiv.setAttribute('id', 'seleniumDiv');
    seleniumDiv.innerHTML =
        '<style>' +
        '.seleniumHighlight{' +
        'box-shadow: 0 0 20px rgba(81, 203, 238, 1) !important;' +
        'padding: 3px 0px 3px 3px;' +
        'margin: 5px 1px 3px 0px;' +
        'border: 3px solid rgba(81, 203, 238, 1) !important;' +
        'background: rgba(81, 203, 238, 1) !important;' +
        '}' +
        '.seleniumrecordLB{' +
        'height: 640px;' +
        'width: 820px;' +
        'z-index: 10001;' +
        '}' +
        '.codeInputTextArea{' +
        'margin-top: 20px;' +
        'height: 600px;' +
        'width: 800px' +
        '}' +
        '.context-menu {' +
        'display: none;' +
        'position: absolute;' +
        'z-index: 9999;' +
        'padding: 12px 0;' +
        'width: 240px;' +
        'background-color: #fff;' +
        'border: solid 1px #dfdfdf;' +
        'box-shadow: 1px 1px 2px #cfcfcf;' +
        '}' +
        '.context-menu--active {' +
        'display: block;' +
        '}' +
        '.context-menu__items {' +
        'list-style: none;' +
        'margin: 0;' +
        'padding: 0;' +
        '}' +
        '.context-menu__item {' +
        'display: block;' +
        'margin-bottom: 4px;' +
        '}' +
        '.context-menu__item_deactivate {' +
        'display: none;' +
        '}' +
        '.context-menu__item:last-child {' +
        'margin-bottom: 0;' +
        '}' +
        '.context-menu__link {' +
        'display: block;' +
        'padding: 4px 12px;' +
        'color: #0066aa;' +
        'text-decoration: none;' +
        '}' +
        '.context-menu__link:hover {' +
        'color: #fff;' +
        'background-color: #0066aa;' +
        '}' +
        '.ghost-select {' +
        'display: none;' +
        'z-index: 9000;' +
        'position: absolute !important;' +
        'cursor: default !important;' +
        '}' +
        '#big-ghost{' +
        'background-color:rgba(239, 28, 190, 0.6);' +
        'border:1px solid #aaf81a;' +
        'position:absolute;' +
        '}' +
        '.ghost-active {' +
        'display: block !important;' +
        '}' +
        '.ghost-select > span {' +
        'background-color: rgba(239, 28, 190, 0.6);' +
        'border: 1px solid #b20e8c;' +
        'width: 100%;' +
        'height: 100%;' +
        'float: left;' +
        '}' +
        '.overlay-ghost-Element.selectedElement{' +
        'background: rgba(0, 239, 67, 0.5) !important;' +
        '}' +
        '.modal {' +
        'display: block;' +
        'position: fixed;' +
        'z-index: 900000;' +
        'padding-top: 100px;' +
        'left: 0;' +
        'top: 0;' +
        'width: 100%;' +
        'height: 100%;' +
        'overflow: auto;' +
        'background-color: rgb(0,0,0);' +
        'background-color: rgba(0,0,0,0.4);' +
        '}' +
        '.modal-content {' +
        'background-color: #fefefe;' +
        'margin: auto;' +
        'padding: 20px;' +
        'border: 1px solid #888;' +
        'width: 80%;' +
        '}' +
        '.close {' +
        'color: #aaaaaa;' +
        'float: right;' +
        'font-size: 28px;' +
        'font-weight: bold;' +
        '}' +
        '.close:hover,' +
        '.close:focus {' +
        'color: #000;' +
        'text-decoration: none;' +
        'cursor: pointer;' +
        '}' +
        '</style>' +
        '<div class="ghost-select"><span></span></div>' +
        '<div id="context-menu" class="context-menu">' +
        '    <ul class="context-menu__items">' +
        '        <li class="context-menu__item">' +
        '            <a class="context-menu__link" onclick="recordScreen()"><i class="fa test"></i>Open Screen Recorder</a>' +
        '        </li>' +
        '        <li class="context-menu__item context-menu__item_select">' +
        '            <a class="context-menu__link" onclick="recordSelectedFieldsMenueItem()" ><i class="fa fa-SelectRec"></i> REC All Selected Fields </a>' +
        '        </li>' +
        '        <li class="context-menu__item">' +
        '            <a class="context-menu__link" onclick="genAllRecordFieldsMenueItem()" ><i class="fa fa-getCodeAll"></i> GEN Recorded Fields Code</a>' +
        '        </li>' +
        '        <li class="context-menu__item">' +
        '            <a class="context-menu__link" onclick="genAllFieldsOfPageMenueItem()" ><i class="fa fa-getCodeAll"></i> GEN All Page Fields </a>' +
        '        </li>' +
        '        <li class="context-menu__item context-menu__item_select">' +
        '            <a class="context-menu__link" onclick="genAllSelectedFieldsMenueItem()" ><i class="fa fa-SelectGenCode"></i> GEN Selected Fields Code </a>' +
        '        </li>' +
        '    </ul>' +
        '</div>' +
        '<div id="overlay-ghost_context-menu" class="context-menu" style="z-index: 10002">' +
        '    <ul class="context-menu__items">' +
        '        <li class="context-menu__item">' +
        '            <a class="context-menu__link" onclick="recordScreenClose()"><i class="fa recordScreen Close"></i>Close Screen Record</a>' +
        '        </li>' +
        '        <li class="context-menu__item">' +
        '            <a class="context-menu__link" onclick="generateTestElements()"><i class="fa test Close"></i> Generate Selected Elements</a>' +
        '        </li>' +
        '    </ul>' +
        '</div>';
    document.body.appendChild(seleniumDiv);

    var fields = [];

    function capitalise(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }

    function camelize(dasherizedStr) {
        return dasherizedStr
            .replace(/-([a-zA-Z])/g, function (m1, m2) {
                return m2.toUpperCase()
            });
    }


    function cleanRecording() {
        fields = [];
    }

    function recordPage() {
        Array.prototype.forEach.call(document.querySelectorAll(selector), function (e) {
            fields.push(e);
        });
    }

    function record(target) {
        if (target.getAttribute('data-seleniumid') == undefined) {
            tempAlert('Keine data-seleniumid-Element gefunden!', 5000);
        } else {
            fields.push(target);
        }
    }

    function createRecordedFieldsCode() {
        var uniqueVals = [];
        Array.prototype.forEach.call(fields, function (el) {
            var found = false;
            Array.prototype.forEach.call(uniqueVals, function (y, elUniqueVal) {
                if (el === elUniqueVal) {
                    found = true;
                    return true;
                }
            });
            if (!found) uniqueVals.push(el);
        });

        generateCode(uniqueVals);
    }

    function generateCode(uniqueVals) {
        var input = document.createElement("textarea");
        input.setAttribute('value', '');
        Array.prototype.forEach.call(uniqueVals, function (el) {
            var val = input.getAttribute('value');
            var selid = el.getAttribute('data-seleniumid');
            var convselid = convertAttributName(el.getAttribute('data-seleniumid'));
            input.setAttribute('value', val + "\t@FindBy(xpath = \"//*[@data-seleniumid='" + selid + "']\")" + "\n\tprivate WebElement " + convselid + ";\n\n");
        });
        Array.prototype.forEach.call(uniqueVals, function (el) {
            var attributeName = el.getAttribute('data-seleniumid');
            attributeName = convertAttributName(attributeName);
            var tagName = el.tagName.toLowerCase().valueOf();
            if (tagName == 'a'.valueOf() || (tagName == 'input' && el.type == 'submit')) {
                var methodName = "click" + capitalise(attributeName);
                input.setAttribute('value', input.getAttribute('value').concat("\tpublic void " + methodName + "(){\n" +
                    "\t\t" + attributeName + ".click(); \n" +
                    "\t}\n\n"));
            }
            if (tagName == 'span'.valueOf() || tagName == 'div'.valueOf() || tagName == 'label'.valueOf()) {
                var methodName = "getTextOf" + capitalise(attributeName);
                input.setAttribute('value', input.getAttribute('value').concat("\tpublic String " + methodName + "(){\n" + "" +
                    "\t\treturn " + attributeName + ".getText();\n" +
                    "\t}\n\n"));
            }
            if (tagName == 'select'.valueOf()) {
                var methodName = "select" + capitalise(attributeName) + "ByValue";
                input.setAttribute('value', input.getAttribute('value').concat("\tpublic void " + methodName + "(String value) {\n" +
                    "\t\t new Select(" + attributeName + ").selectByValue(value);\n" +
                    "\t}\n\n"));

                var methodName = "select" + capitalise(attributeName) + "ByLabel";
                input.setAttribute('value', input.getAttribute('value').concat("\tpublic void " + methodName + "(String label) {\n" +
                    "\t\t new Select(" + attributeName + ").selectByVisibleText(label);\n" +
                    "\t}\n\n"));

                var methodName = "select" + capitalise(attributeName) + "ByIndex";
                input.setAttribute('value', input.getAttribute('value').concat("\tpublic void " + methodName + "(int index) {\n" +
                    "\t\t new Select(" + attributeName + ").selectByIndex(index);\n" +
                    "\t}\n\n"));

                //get selected für alle 3
            }
            if (tagName == 'textarea'.valueOf()) {
                var methodName = "enter" + capitalise(attributeName);
                input.setAttribute('value', input.getAttribute('value').concat("\tpublic void " + methodName + "(CharSequence input) {\n" +
                    "\t\t" + attributeName + ".sendKeys(Keys.chord(Keys.CONTROL, \"a\"), input);\n" +
                    "\t}\n\n"));
                methodName = "getValueOf" + capitalise(attributeName);
                input.setAttribute('value', input.getAttribute('value').concat("\tpublic String " + methodName + "(){\n" +
                    "\t\treturn " + attributeName + ".getAttribute(\"value\");\n" +
                    "\t}\n\n"));
            }
            if (tagName == 'input'.valueOf()) {
                switch (el.type) {
                    case "text":
                        var methodName = "enter" + capitalise(attributeName);
                        input.setAttribute('value', input.getAttribute('value').concat("\tpublic void " + methodName + "(CharSequence input) {\n" +
                            "\t\t" + attributeName + ".sendKeys(Keys.chord(Keys.CONTROL, \"a\"), input);\n" +
                            "\t}\n\n"));
                        methodName = "getValueOf" + capitalise(attributeName);
                        input.setAttribute('value', input.getAttribute('value').concat("\tpublic String " + methodName + "(){\n" +
                            "\t\treturn " + attributeName + ".getAttribute(\"value\");\n" +
                            "\t}\n\n"));
                        break;
                    case "image":
                        var methodName = "click" + capitalise(attributeName);
                        input.setAttribute('value', input.getAttribute('value').concat("\tpublic void " + methodName + "(){\n" +
                            "\t\t" + attributeName + ".click(); \n" +
                            "\t}\n\n"));
                        break;
                    case "checkbox":
                        break;
                    case "radio":
                        break;
                }
            }

        });
        if (uniqueVals.length == 0) {
            tempAlert("Keine Elemente für die Generierung gefunden", 5000)
        } else {
            displayGeneratedCode(input.getAttribute('value'));
        }
    }

    function displayGeneratedCode(val) {
        recordScreenClose();
        var modal = document.createElement("div");
        modal.setAttribute('class', 'modal');
        modal.innerHTML =
            '<div class="modal-content">' +
            '<span class="close" onclick="hideGeneratedCode();">&times;</span>' +
            '<textarea class="codeInputTextArea">' + val + '</textarea>' +
            '</div>';
        document.querySelector('.ghost-select').parentElement.appendChild(modal);
        cleanRecording();
    }

    function hideGeneratedCode() {
        var element = document.querySelector(".modal");
        element.parentNode.removeChild(element);
    }

    function tempAlert(msg, duration) {
        var el = document.createElement("div");
        el.setAttribute("style", "position:absolute;top:" + menuPositionY + "px;left:" + menuPositionX + "px;background-color:#B9C5C8;font:bold;z-index:9999;border:2px black solid;");
        el.innerHTML = msg;
        setTimeout(function () {
            el.parentNode.removeChild(el);
        }, duration);
        document.body.appendChild(el);
    }

    function convertAttributName(text) {
        text = text.charAt(0).toLowerCase() + text.slice(1);
        text = camelize(text);
        return text;
    }

    function clickInsideElement(e, className) {
        var el = e.srcElement || e.target;

        if (el.classList.contains(className)) {
            return el;
        } else {
            while (el = el.parentNode) {
                if (el.classList && el.classList.contains(className)) {
                    return el;
                }
            }
        }

        return false;
    }

    function getPosition(e) {

        if (!e) var e = window.event;

        var posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
        var posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;

        return {
            x: posx,
            y: posy
        }
    }

    var clickedTarget;
    var contextMenuActive = "context-menu--active";

    var clickCoords;

    var menu;
    var menuState = 0;
    var menuPositionX;
    var menuPositionY;

    var menuGhost;
    var menuGhostState = 0;
    var menuGhostPositionX;
    var menuGhostPositionY;

    function initMenue() {
        menu = document.querySelector("#context-menu");
        menuGhost = document.querySelector("#overlay-ghost_context-menu");
        contextListener();
        keyupListener();
        resizeListener();
    }

    function contextListener() {
        document.addEventListener("click", function (e) {
            if (!e.target.classList.contains("context-menu") && !e.target.classList.contains("context-menu__item")) {
                toggleMenuOff();
                toggleGhostMenuOff();
            }
        });
        document.addEventListener("contextmenu", function (e) {
            if (e.target.classList.contains("overlay-ghost")) {
                toggleGhostMenuOn();
                positionMenuGhost(e);
            } else {
                clickedTarget = e.target;
                toggleMenuOn();
                positionMenu(e);
            }

            e.preventDefault();
        });
    }

    function keyupListener() {
        window.onkeyup = function (e) {
            if (e.keyCode === 27) {
                toggleMenuOff();
                toggleGhostMenuOff();
            }
        }
    }

    function resizeListener() {
        window.onresize = function (e) {
            toggleMenuOff();
            toggleGhostMenuOff();
        };
    }

    function toggleMenuOn() {
        if (menuState !== 1) {
            if (selectionfields.length == 0) {
                document.querySelector(".context-menu__item_select").classList.add("context-menu__item_deactivate");
            } else {
                document.querySelector(".context-menu__item_select").classList.remove("context-menu__item_deactivate");
            }
            menuState = 1;
            menu.classList.add(contextMenuActive);
        }
    }

    function toggleGhostMenuOn() {
        if (menuGhostState !== 1) {
            if (selectionfields.length == 0) {
                document.querySelector(".context-menu__item_select").classList.add("context-menu__item_deactivate");
            } else {
                document.querySelector(".context-menu__item_select").classList.remove("context-menu__item_deactivate");
            }
            menuGhostState = 1;
            menuGhost.classList.add(contextMenuActive);
        }
    }

    function toggleMenuOff() {
        if (menuState !== 0) {
            menuState = 0;
            clickedTarget = null;
            menu.classList.remove(contextMenuActive);
        }
    }

    function toggleGhostMenuOff() {
        if (menuGhostState !== 0) {
            menuGhostState = 0;
            clickedTarget = null;
            menuGhost.classList.remove(contextMenuActive);
        }
    }

    function positionMenu(e) {
        clickCoords = getPosition(e);
        menuPositionX = clickCoords.x;
        menuPositionY = clickCoords.y;
        menu.style.left = menuPositionX + "px";
        menu.style.top = menuPositionY + "px";
    }

    function positionMenuGhost(e) {
        clickCoords = getPosition(e);
        menuGhostPositionX = clickCoords.x;
        menuGhostPositionY = clickCoords.y;
        menuGhost.style.left = menuGhostPositionX + "px";
        menuGhost.style.top = menuGhostPositionY + "px";
    }

    function recordScreen() {
        var ghost = document.createElement("div");
        ghost.setAttribute('id', 'overlay-ghost');
        ghost.setAttribute('class', 'overlay-ghost');
        ghost.setAttribute('style', "background: rgba(0, 6, 239, 0.1); position:absolute;width: " + document.documentElement.scrollWidth + "px; height:" + document.documentElement.scrollHeight + "px; top:0px; left:0px; z-index: 10000");
        document.body.appendChild(ghost);
        Array.prototype.forEach.call(
            document.querySelectorAll(selector), function (e) {
                console.log(e);
                if (offset(e).top > 0) {
                    var top = offset(e).top;
                    var left = offset(e).left;
                    var width = parseInt(e.offsetWidth);
                    var height = parseInt(e.offsetHeight);
                    var div = document.createElement('div');
                    div.setAttribute('id', 'overlay-ghost-' + e.getAttribute('data-seleniumid'))
                    div.setAttribute('class', 'overlay-ghost-Element')
                    div.setAttribute('style', "border:1px black solid; background: rgba(0, 6, 239, 0.3); position:absolute;width: " + width + "px; height:" + height + "px; top:" + top + "px; left:" + left + "px; z-index:20000;");
                    div.addEventListener('click', function (e) {
                        selectGhostElement(e.target)
                    });
                    var span = document.createElement('span');
                    span.setAttribute('style', 'display: none !important;');
                    span.innerText = e.getAttribute('data-seleniumid');
                    div.appendChild(span);
                    document.querySelector("#overlay-ghost").appendChild(div);
                }
            });
    }

    function generateTestElements() {
        cleanRecording();
        Array.prototype.forEach.call(document.querySelectorAll('.selectedElement'), function (e) {
            record(document.querySelector('[data-seleniumid=' + e.querySelector("span").innerText + ']'));
        });
        createRecordedFieldsCode();
        selectionfields = [];
    }

    function recordScreenClose() {
        var element = document.querySelector("#overlay-ghost");
        console.log(element);
        element.parentNode.removeChild(element);
    }

    function selectGhostElement(element) {
        element.classList.contains("selectedElement") ? element.classList.remove("selectedElement") : element.classList.add("selectedElement");
    }

    function genAllRecordFieldsMenueItem() {
        createRecordedFieldsCode();
    }

    function genAllFieldsOfPageMenueItem() {
        cleanRecording();
        recordPage();
        createRecordedFieldsCode();
    }

    function recordSelectedFieldsMenueItem() {
        Array.prototype.forEach.call(selectionfields, function (i, el) {
            record(el);
        });
        selectionfields = [];
    }

    function genAllSelectedFieldsMenueItem() {
        cleanRecording();
        Array.prototype.forEach.call(selectionfields, function (i, el) {
            record(el);
        });
        createRecordedFieldsCode();
        selectionfields = [];
    }

    var selectionfields = [];
    document.addEventListener("mousedown", function (e) {
        if (document.querySelectorAll(".big-ghost")) {
            Array.prototype.forEach.call(document.querySelectorAll(".big-ghost"), function (element) {
                element.parentNode.removeChild(element);
            });

        }
        document.querySelector(".ghost-select").classList.remove("ghost-active");
        if (e.button === 2) {
            document.querySelector(".ghost-select").classList.add("ghost-active");
            document.querySelector(".ghost-select").style.left = e.pageX;
            document.querySelector(".ghost-select").style.top = e.pageY;

            initialW = e.pageX;
            initialH = e.pageY;

            document.addEventListener("mouseup", selectElements);
            document.addEventListener("mousemove", openSelector);
        }
    });


    function selectElements(e) {
        document.removeEventListener("mousemove", openSelector);
        document.removeEventListener("mouseup", selectElements);
        var maxX = 0;
        var minX = 5000;
        var maxY = 0;
        var minY = 5000;
        Array.prototype.forEach.call(document.querySelectorAll('[data-seleniumid]'), function (e) {
            var aElem = document.querySelector(".ghost-select");
            var bElem = e;
            var result = doObjectsCollide(aElem, bElem);

            if (result == true) {
                selectionfields.push(bElem);
                var aW = bElem.offsetWidth;
                var aH = bElem.offsetHeight;
                var bW = bElem.offsetWidth;
                var bH = bElem.offsetHeight;
                var coords = checkMaxMinPos(offset(bElem).top, offset(bElem).left, offset(bElem).top, offset(bElem).left, aW, aH, bW, bH, maxX, minX, maxY, minY);
                maxX = coords.maxX;
                minX = coords.minX;
                maxY = coords.maxY;
                minY = coords.minY;
                var parent = bElem.parentNode;
                if (bElem.style && (bElem.style.left === "auto" && bElem.style.top === "auto")) {
                    bElem.style.left = parent.style.left;
                    bElem.style.top = parent.style.top;
                }
                var bigGhost = document.querySelector('#big-ghost');
                if (!bigGhost) {
                    bigGhost = document.createElement('div');
                    bigGhost.setAttribute('id', 'big-ghost');
                    bigGhost.setAttribute('class', 'big-ghost');
                    document.body.appendChild(bigGhost);
                }
                bigGhost.style.width = parseInt(maxX) + parseInt(40) - parseInt(minX);
                bigGhost.style.height = parseInt(maxY) + parseInt(20) - parseInt(minY);
                bigGhost.style.top = parseInt(minY) - parseInt(10);
                bigGhost.style.left = parseInt(minX) - parseInt(20);
            }
        });
        document.querySelector(".ghost-select").classList.remove("ghost-active");
        document.querySelector(".ghost-select").style.width = 0;
        document.querySelector(".ghost-select").style.height = 0;
    }

    function openSelector(e) {
        var w = Math.abs(initialW - e.pageX);
        var h = Math.abs(initialH - e.pageY);

        document.querySelector(".ghost-select").style.width = w;
        document.querySelector(".ghost-select").style.height = h;
        if (e.pageX <= initialW && e.pageY >= initialH) {
            document.querySelector(".ghost-select").style.left = e.pageX;
        } else if (e.pageY <= initialH && e.pageX >= initialW) {
            document.querySelector(".ghost-select").style.topn = e.pageY;
        } else if (e.pageY < initialH && e.pageX < initialW) {
            document.querySelector(".ghost-select").style.left = e.pageX;
            document.querySelector(".ghost-select").style.top = e.pageY;
        }
    }


    function doObjectsCollide(a, b) {
        var aTop = offset(a).top;
        var aLeft = offset(a).left;
        var bTop = offset(b).top;
        var bLeft = offset(b).left;

        return !(
            ((aTop + a.offsetHeight) < (bTop)) ||
            (aTop > (bTop + b.offsetHeight)) ||
            ((aLeft + a.offsetWidth) < bLeft) ||
            (aLeft > (bLeft + b.offsetWidth))
        );
    }

    function checkMaxMinPos(aTop, aLeft, bTop, bLeft, aW, aH, bW, bH, maxX, minX, maxY, minY) {
        'use strict';
        if (aLeft < bLeft) {
            if (aLeft < minX) {
                minX = aLeft;
            }
        } else {
            if (bLeft < minX) {
                minX = bLeft;
            }
        }

        if (aLeft + aW > bLeft + bW) {
            if (aLeft > maxX) {
                maxX = aLeft + aW;
            }
        } else {
            if (bLeft + bW > maxX) {
                maxX = bLeft + bW;
            }
        }
        if (aTop < bTop) {
            if (aTop < minY) {
                minY = aTop;
            }
        } else {
            if (bTop < minY) {
                minY = bTop;
            }
        }

        if (aTop + aH > bTop + bH) {
            if (aTop > maxY) {
                maxY = aTop + aH;
            }
        } else {
            if (bTop + bH > maxY) {
                maxY = bTop + bH;
            }
        }

        return {
            'maxX': maxX,
            'minX': minX,
            'maxY': maxY,
            'minY': minY
        };
    }

    initMenue();

}