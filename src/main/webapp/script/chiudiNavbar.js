function chiudiNavbar() {
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