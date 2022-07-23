function apriNavbar() {
    let dropdowns = document.querySelectorAll('.notnav');
    let i;
    for (i = 0; i < dropdowns.length; i++) {
        let openDropdown = dropdowns[i];
        openDropdown.classList.remove("notnav");
        openDropdown.classList.add("yesnav");
    }
}

