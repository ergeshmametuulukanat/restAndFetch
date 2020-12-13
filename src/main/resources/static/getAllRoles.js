function getRoles() {
    fetch("http://localhost:8088/getAllRoles").then((res) => res.json())
        .then((data) => {
            let output = "";
            data.forEach(function (role) {
                output += `<option>${role.name}</option>`;
            });
            document.getElementById("roleNew").innerHTML = output;
            document.getElementById("roleEdit").innerHTML = output;
            document.getElementById("roleDelete").innerHTML = output;
        })
}

getRoles()