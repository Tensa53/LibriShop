function dropdownMenuR() {

    let dropdowns3 = document.getElementsByClassName("dropdown-content");
    let i;
    for (i = 0; i < dropdowns3.length; i++) {
        let openDropdown3 = dropdowns3[i];
        if (openDropdown3.classList.contains("show")) {
            openDropdown3.classList.remove("show");
        }

        let dropdowns = document.getElementsByClassName("dropdown-content utenter");
        let j;
        for (j = 0; j < dropdowns.length; j++) {
            let openDropdown = dropdowns[j];
            openDropdown.classList.add("show");
        }

        // chiude il menù quando si clicca altrove
        window.onclick = function (event) {
            if (!event.target.matches("#iconaR")) {
                let dropdowns2 = document.getElementsByClassName("dropdown-content");
                let i;
                for (i = 0; i < dropdowns2.length; i++) {
                    let openDropdown2 = dropdowns2[i];
                    if (openDropdown2.classList.contains("show")) {
                        openDropdown2.classList.remove("show");
                    }
                }
            }
        }
    }
}
