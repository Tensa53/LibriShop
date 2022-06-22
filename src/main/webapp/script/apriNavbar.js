function apriNavbar() {

    let dropdowns = document.querySelectorAll('.notnav');
    document.getElementById("bottonenav").innerText = "↑";
    let i;
    for (i = 0; i < dropdowns.length; i++) {
        let openDropdown = dropdowns[i];
        openDropdown.classList.remove("notnav");
        openDropdown.classList.add("yesnav");
    }

    window.onclick = function (event) {
        if (!event.target.matches("#bottonenav")) {
            document.getElementById("bottonenav").innerText = "↓";
            let dropdowns1 = document.querySelectorAll('.yesnav');
            let i;
            for (i = 0; i < dropdowns1.length; i++) {
                let openDropdown1 = dropdowns1[i];
                if (openDropdown1.classList.contains("yesnav")) {
                    openDropdown1.classList.remove("yesnav");
                    openDropdown1.classList.add("notnav");
                }
            }
        }
    }
}