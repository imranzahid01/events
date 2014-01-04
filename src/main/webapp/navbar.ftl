<div class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">Events</a>
  </div>
  <div class="navbar-collapse collapse">
    <ul class="nav navbar-nav">
      <li class="[#if piIs("/")]active[/#if]"><a href="${_r.contextPath}/">Home</a></li>
      <li class="[#if piIs("/settings")]active[/#if]"><a href="${_r.contextPath}/settings">Settings</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li>${_r.user.fullName} <a id="logout" href="#">(Logout)</a></li>
    </ul>
  </div>
</div>
<script type="text/javascript">
  $(function() {
    $('#logout').on("click", function() {
      $.ajax({
        url: "${_r.contextPath}/api/logoff",
        data: "",
        success: function(data) {
          if (data.success) {
            window.location = "${_r.contextPath}/";
          }
          else {
            var msg = data.errorMessage || data.errorCode || "can't logoff.";
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
  });
</script>
