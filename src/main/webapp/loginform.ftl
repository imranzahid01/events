<div class="container">
  <form id="login-form" class="form-signin" role="form" action="${_r.contextPath}/api/login" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="text" name="userEmail" id="login-username" class="form-control" placeholder="Email address" required autofocus>
    <input type="password" name="userPass" id="login-password" class="form-control" placeholder="Password" required>
    <label class="checkbox">
      <input type="checkbox" name="userRemember" value="remember-me"> Remember me
    </label>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>
</div>
<script type="text/javascript">
  $(function() {
    var $form = $("#login-form");
    $form.ajaxForm({
      dataType: "json",
      success: function(data){
        if (data.success) {
          window.location = "${_r.contextPath}/";
        }
        else {
          var msg = data.errorMessage || data.errorCode || "wrong user/password. Try registering.";
          noty({
            layout: "topCenter",
            type: "error",
            text: msg,
            timeout: 2000
          });
        }
      }
    });
  });
</script>
