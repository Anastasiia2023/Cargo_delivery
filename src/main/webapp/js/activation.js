document.querySelectorAll('li > a').forEach((nav) => {
  let startPath = "/Cargo_delivery_epam/";
  let index = "/Cargo_delivery_epam/index.jsp";
  if (nav.pathname === window.location.pathname ||
      window.location.pathname === startPath && nav.pathname === index) {
    nav.classList.add('active')
  }
})