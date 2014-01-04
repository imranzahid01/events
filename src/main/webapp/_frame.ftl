<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title> :: Events :: </title>

  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
  [#if _r.user??]
  <link rel="stylesheet" type="text/css" href="css/site.css">
  [#else]
  <link rel="stylesheet" type="text/css" href="css/signin.css">
  [/#if]
  <link rel="stylesheet" type="text/css" media="screen" href="css/vendor/cupertino/jquery-ui-1.10.3.custom.min.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="css/vendor/ui.jqgrid.css" />
  <script src="js/vendor/modernizr-2.6.2.min.js"></script>
  <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
  <script>window.jQuery || document.write('<script src="js/vendor/jquery-2.0.3.min.js"><\/script>')</script>
  <script src="js/plugins.js"></script>
  <script src="js/vendor/i18n/grid.locale-en.js"></script>
  <script src="js/vendor/jquery.jqGrid.min.js"></script>
  <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
  <script src="js/vendor/jquery.noty.packaged.min.js"></script>
  <script src="js/vendor/jquery.form.min.js"></script>

  <style type="text/css">
    #events_footer {
      padding-right: 0;
      padding-left: 0;
      position: fixed;
      right: 0;
      left: 0;
      bottom: 0;
      margin-bottom: 0;
    }
  </style>
</head>

<body>
  <!--[if lt IE 7]>
  <p class="browsehappy">You are using an <strong>outdated</strong> browser.
                         Please <a href="http://browsehappy.com/">upgrade your browser</a>
                         to improve your experience.</p>
  <![endif]-->
  [#if _r.user??]
    <!-- Note:
    "includeFrameContent" is a Snow specific freemarker directive
    that allow to include the targeted template for this URL
    respecting the "_frame.ftl" hierarchy  -->
    [@includeFrameContent /]
  [#else]
    [@includeTemplate name="loginform.ftl"/]
  [/#if]
  <div id="events_footer" class="well well-sm">
    Events v${version}
  </div>
</body>
</html>
