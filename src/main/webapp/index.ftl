<div class="container">
  [@includeTemplate name="navbar.ftl" /]
  <table id="list"></table>
  <div id="pager"></div>
  <script type="text/javascript">
    $(function() {

      $.ajax({
        url: "${_r.contextPath}/api/events/${api}/list",
        data: "",
        success: function(data) {
          if (data.success) {
            console.log(data);
          }
          else {
            var msg = data.errorMessage || data.errorCode || "unexpected error";
            noty({
              layout: "topCenter",
              type: "error",
              text: msg,
              timeout: 2000
            });
          }
        },
        dataType: "json"
      });
    });
  </script>
</div>
