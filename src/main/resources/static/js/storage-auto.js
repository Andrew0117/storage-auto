let currentEditRow = null;

function openAddModal() {
    document.getElementById("addModal").style.display = "block";
}

function closeAddModal() {
    document.getElementById("addModal").style.display = "none";
}

function saveNewCar() {
    const brand = document.getElementById("addBrand").value.trim();
    const model = document.getElementById("addModel").value.trim();
    const comment = document.getElementById("addComment").value.trim();
    const color = document.getElementById("addColor").value.trim();
    const price = document.getElementById("addPrice").value.trim();

    if (!brand || !model) {
        alert("Please fill in Brand and Model.");
        return;
    }

    let storageAuto = JSON.stringify({
        "brand": brand,
        "model": model,
        "comment": comment,
        "color": color,
        "price": price
    });

    let result = post("/api/storage", storageAuto);

    if ("undefined" === typeof result || result === null) {
        return;
    }

    const table = document.getElementById("carTable").querySelector("tbody");
    const row = document.createElement("tr");
    row.id = result.idBrand + ", " + result.idModel;
    row.innerHTML = `
        <td>${result.brand}</td>
        <td>${result.model}</td>
        <td>${result.comment}</td>
        <td><span class="color-circle" style="background-color: ${result.color};"></span>${getColorName(result.color)}</td>
        <td>$${Number(result.price).toLocaleString()}</td>
        <td class="actions"><button class="btn-delete" onclick="deleteRow(this)">Delete</button></td>
        <td class="actions"><button class="btn-change" onclick="openChangeModal(this)">Change</button></td>
        `;
    table.appendChild(row);
    closeAddModal();
}

function getColorName(hex) {
    const map = {
        "#000000": "Black",
        "#ffffff": "White",
        "#ff0000": "Red",
        "#0000ff": "Blue",
        "#00ff00": "Green",
    };
    return map[hex.toLowerCase()] || hex;
}

function deleteRow(btn) {
    const row = btn.closest("tr");
    let id = row.id;
    let parts = id.split(',');

    delete_("/api/storage/" + parts[0] + "/" + parts[1] + "/");

    row.remove();
}

let commentBrand = $('#commentBrand');  // commentModel
commentBrand.on('change', function (event) {
    let value = $(event.currentTarget).find("option:selected").val();
    let commentModel = $('#commentModel').get(0);

    let result = get("/api/model/" + value);

    while (commentModel.options.length > 0) {
        commentModel.remove(commentModel.options.length - 1);
    }
    if ("undefined" !== typeof result && result !== null) {
        for (let i = 0; i < result.length; i++) {
            let model = result[i];
            let opt = document.createElement('option');
            opt.text = model.model;
            opt.value = model.id;
            commentModel.add(opt, null);
        }
    }
});

function openCommentModal() {
    let result = get("/api/brand");

    let commentBrand = $('#commentBrand').get(0);
    while (commentBrand.options.length > 0) {
        commentBrand.remove(commentBrand.options.length - 1);
    }
    if ("undefined" !== typeof result && result !== null) {
        for (let i = 0; i < result.length; i++) {
            let brand = result[i];
            let opt = document.createElement('option');
            opt.text = brand.brand;
            opt.value = brand.id;
            commentBrand.add(opt, null);
        }
    }

    let value = $('#commentBrand').find("option:selected").val();
    let commentModel = $('#commentModel').get(0);

    result = get("/api/model/" + value);

    while (commentModel.options.length > 0) {
        commentModel.remove(commentModel.options.length - 1);
    }
    if ("undefined" !== typeof result && result !== null) {
        for (let i = 0; i < result.length; i++) {
            let model = result[i];
            let opt = document.createElement('option');
            opt.text = model.model;
            opt.value = model.id;
            commentModel.add(opt, null);
        }
    }

    document.getElementById("commentModal").style.display = "block";
}

function closeCommentModal() {
    document.getElementById("commentModal").style.display = "none";
}

function saveComment() {
    let valueModel = $('#commentModel').find("option:selected").val();
    let commentText = document.getElementById("commentText").value.trim();

    if (!commentText) {
        alert("Please fill in Comment Text.");
        return;
    }

    let commentModel = JSON.stringify({
        "id": valueModel,
        "comment": commentText
    });

    post("/api/model/comment", commentModel);

    const rows = document.querySelectorAll("#carTable tbody tr");

    rows.forEach(row => {
        let parts = row.id.split(',');
        if (parts[1].trim() === valueModel) {
            row.children[2].innerHTML = commentText;
        }
    });

    closeCommentModal();
}

function openChangeModal(btn) {
    currentEditRow = btn.closest("tr");
    const colorCircle = currentEditRow.querySelector(".color-circle");
    const priceCell = currentEditRow.children[4];

    document.getElementById("editColor").value = rgbToHex(window.getComputedStyle(colorCircle).backgroundColor);
    document.getElementById("editPrice").value = priceCell.textContent.substring(1);

    document.getElementById("changeModal").style.display = "block";
}

function closeChangeModal() {
    document.getElementById("changeModal").style.display = "none";
}

function saveChange() {
    const color = document.getElementById("editColor").value;
    const price = document.getElementById("editPrice").value;

    let parts = currentEditRow.id.split(',');

    let model = JSON.stringify({
        "id": parts[1],
        "color": color,
        "price": price
    });

    post("/api/model/color/price", model);

    currentEditRow.children[3].innerHTML = `<span class="color-circle" style="background-color: ${color};"></span>${getColorName(color)}`;
    currentEditRow.children[4].textContent = `$${Number(price).toLocaleString()}`;

    closeChangeModal();
}

function rgbToHex(rgb) {
    const result = rgb.match(/\d+/g);
    return "#" + result.map(x => (+x).toString(16).padStart(2, "0")).join("");
}

window.onclick = function (e) {
    if (e.target.classList.contains("modal")) {
        e.target.style.display = "none";
    }
};