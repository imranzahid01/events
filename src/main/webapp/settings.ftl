<div class="container">
  [@includeTemplate name="navbar.ftl" /]
  <h1>Freemarker default <em>_r.*</em> properties </h1>
  <ul class="list-group">
    <li class="list-group-item"><em>[#noparse]${_r.contextPath}[/#noparse]</em>: "${_r.contextPath}"</li>
    <li class="list-group-item"><em>[#noparse]${_r.fullPath}[/#noparse]</em>: "${_r.fullPath}"</li>
    <li class="list-group-item"><em>[#noparse]${_r.href}[/#noparse]</em>: "${_r.href}"</li>
    <li class="list-group-item"><em>[#noparse]${_r.pathInfo}[/#noparse]</em>: "${_r.pathInfo}"</li>
    <li class="list-group-item"><em>[#noparse]${_r.user}[/#noparse]</em>: "${_r.user}"</li>
  </ul>
</div>
