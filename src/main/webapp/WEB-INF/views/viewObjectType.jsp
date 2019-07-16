<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Admin Tools</title>
    <!-- Bootstrap -->
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/bootstrap-treeview.min.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="resources/js/bootstrap-treeview.js"></script>
</head>
<body>

<table class="table table-hover">
    <th width=310>Object_types</th>
    <th width=310>Atributes</th>
    <th width=310>Info</th>
    <tr>
        <td>
            <div id="Tree"></div>
        </td>
        <td>
            <form method="GET" action="/deleteAttribute">
                <c:forEach var="i" items="${AttrList}">
                    <p><input type="checkbox" name="id" value="${i.id}"> ${i.name}<Br></p>
                </c:forEach>
                <input type="hidden" name="ido" value="${parametr}">
                <p><input type="submit" class="btn btn-danger btn-sm" value="Delete Attribute"></p>
            </form>
            <form method="GET" action="/createAttribute">
                <input type="hidden" name="ido" value="${parametr}">
                <input type="submit" class="btn btn-primary btn-sm" value="Create new Attribute">
            </form>
        </td>
        <td>
            <c:forEach var="contact" items="${list}" varStatus="status">

                <c:if test="${contact.id==parametr}">
                    <p>&nbsp; Parant_id= ${contact.parentId}</p>
                    <p>&nbsp; Object_type_id= ${contact.id}</p>
                    <p>&nbsp; name= ${contact.name}</p>
                </c:if>
            </c:forEach>
        </td>

    </tr>
    <tr>
        <td>
            <form method="GET" action="/createObjecType">
                <input type="hidden" name="ido" value="${parametr}">
                <input type="submit" class="btn btn-primary btn-md" value="Create new Object_type">
            </form>
        </td>
    </tr>
</table>

<br><br>
<div class="col-md-4">
    <form action="/createTemp" method="post">
        <div>
            <c:forEach var="contact" items="${list}" varStatus="status">
                <c:if test="${contact.id==parametr}">
                    <div class="form-group">
                        <input class="form-control" type="hidden" name="objType" value="${contact.id}">
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="hidden" name="parentId" value="${contact.parentId}">
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="hidden" name="nameObj" value="${contact.name}">
                    </div>
                    <c:forEach var="attr" items="${AttrList}" varStatus="status">
                        <div class="form-group">
                            <input class="form-control" type="hidden" name="attrId" value="${attr.id}">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="hidden" name="nameField" value="${attr.name}">
                        </div>
                    </c:forEach>
                </c:if>
            </c:forEach>
            <td><input type="submit" class="btn btn-danger btn-large" value="Сохранить">
            <td>
                <a class="btn btn-danger btn-large" href="D:/downloads/Test/src/main/webapp/resources/newClass.java"
                   download>Download</a>
        </div>
    </form>
</div>

<div id="selectable-output"></div>

</form></td>
</tr>

<script>
    var initSelectableTree = function () {
        return $('#Tree').treeview({
            levels: 5,
            enableLinks: true,
            data: ${listContact},
            multiSelect: $('#chk-select-multi').is(':checked'),
            onNodeSelected: function (event, node) {
                $('#rost').prepend('<p>' + node.obtypeId + ' was selected</p>');
            },
            onNodeUnselected: function (event, node) {
                $('#selectable-output').prepend('<p>' + node.text + ' was unselected</p>');
            }
        });
    };
    var $selectableTree = initSelectableTree();
    var findSelectableNodes = function () {
        return $selectableTree.treeview('search', [$('#input-select-node').val(), {
            ignoreCase: false,
            exactMatch: false
        }]);
    };
    var selectableNodes = findSelectableNodes();
    $('#chk-select-multi:checkbox').on('change', function () {
        console.log('multi-select change');
        $selectableTree = initSelectableTree();
        selectableNodes = findSelectableNodes();
    });
    // Select/unselect/toggle nodes
    $('#input-select-node').on('keyup', function (e) {
        selectableNodes = findSelectableNodes();
        //noinspection JSUnresolvedFunction
        $('.select-node').prop('disabled', !(selectableNodes.length >= 1));
    });
    $('#btn-select-node.select-node').on('click', function (e) {
        $selectableTree.treeview('selectNode', [selectableNodes, {silent: $('#chk-select-silent').is(':checked')}]);
    });
    $('#btn-unselect-node.select-node').on('click', function (e) {
        $selectableTree.treeview('unselectNode', [selectableNodes, {silent: $('#chk-select-silent').is(':checked')}]);
    });
    $('#btn-toggle-selected.select-node').on('click', function (e) {
        $selectableTree.treeview('toggleNodeSelected', [selectableNodes, {silent: $('#chk-select-silent').is(':checked')}]);
    });

</script>
</body>
</html>