<!DOCTYPE html>
<html>
<head>
    <title>Exemple de graphique en camembert avec Chart.js</title>
    <script src="<%= request.getContextPath() %>/assets/chartjs/Chart.min.js"></script>
</head>
<body>
    <canvas id="camembertChart" width="400" height="400"></canvas>

    <script>
        // Données du graphique
        var data = [
            {
                value: 30,
                color: "#F7464A",
                highlight: "#FF5A5E",
                label: "Catégorie 1"
            },
            {
                value: 50,
                color: "#46BFBD",
                highlight: "#5AD3D1",
                label: "Catégorie 2"
            },
            {
                value: 20,
                color: "#FDB45C",
                highlight: "#FFC870",
                label: "Catégorie 3"
            }
        ];

        // Options du graphique
        var options = {
            responsive: false,
            animation: {
                animateScale: false
            }
            ,legend: {
                display: true,
                position: 'bottom',
                fontSize: 20 // Vous pouvez changer la position de la légende ici (top, left, right, bottom)
            }
        };

        // Créer le graphique en camembert
        var ctx = document.getElementById("camembertChart").getContext("2d");
        var myPieChart = new Chart(ctx).Pie(data, options);
    </script>
</body>
</html>
