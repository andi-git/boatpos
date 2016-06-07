<?php
include 'security.php';
include 'config.php';
setlocale(LC_MONETARY, 'de_DE');

// read input and decode it
$myRentalId = Security::decrypt($_GET["id"], $key);
$myRentalIdExploded = explode("_", $myRentalId);
$dateExploded = explode("-", $myRentalIdExploded[0]);
$year = $dateExploded[0];
$month = $dateExploded[1];
$day = $dateExploded[2];
$dayId = $myRentalIdExploded[1];

$curl = curl_init();
curl_setopt($curl, CURLOPT_URL, "http://home.com:8180/boatpos-server/rest/rental/$year/$month/$day/$dayId");
curl_setopt($curl, CURLOPT_HTTPHEADER, array(
    'Accept: application/json',
    'Content-Type: application/json',
    'username:' . $username,
    'password:' . $password
));
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, false);
curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
$response = curl_exec($curl);
$code = curl_getinfo($curl, CURLINFO_HTTP_CODE);

// log
file_put_contents("myrental.log", date("Y-m-d H:i:s", time()) . " " . "$year-$month-$day $dayId $code\n", FILE_APPEND);

curl_close($curl);
?>

    <html>
    <head>
        <title>Bootsvermietung EPPEL - meine Bootsfahrt</title>
        <link rel="stylesheet" href="boatpos.css">
        <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    </head>
<body>
    <h1 class="header header-main">&nbsp;&nbsp;&nbsp;EPPEL - Meine Bootsfahrt!</h1>
    <img class="image-sun" src="logo.png" height="200px"/>

<?php
if ($code != 200) {
    echo "<p>Es ist leider ein Fehler aufgetreten!</p>";
} else {
    $rental = json_decode($response, true);
    if ($rental['deleted'] || $rental['finished']) {
        echo "<p>Diese Bootsmiete ist nicht mehr aktuell...</p>";
    } else {
        ?>
        <table class="table-no-style">
            <tr class="table-no-style">
                <td class="table-no-style">
                    <table class="table-no-style">
                        <tr class="table-no-style">
                            <td class="table-no-style"><b>Boot</b></td>
                            <td class="table-no-style"><b><?php echo $rental['boatBean']['name']; ?></b></td>
                        </tr>
                        <tr class="table-no-style">
                            <td class="table-no-style"><b>Datum</b></td>
                            <td class="table-no-style"><?php
                                $dayExploded = explode("-", $rental['day']);
                                echo $dayExploded[2] . ". " . $dayExploded[1] . ". " . $dayExploded[0];
                                ?></td>
                        </tr>
                        <tr class="table-no-style">
                            <td class="table-no-style"><b>Nummer</b></td>
                            <td class="table-no-style"><b><?php echo sprintf('%03d', $rental['dayId']); ?></b></td>
                        </tr>
                        <tr class="table-no-style">
                            <td class="table-no-style"><b>Abfahrt</b></td>
                            <td class="table-no-style"><?php
                                $d = new DateTime($rental['departure']);
                                echo $d->format("H:i"); ?> Uhr
                            </td>
                        </tr>
                        <tr class="table-no-style">
                            <td class="table-no-style"><b>Uhrzeit</b></td>
                            <td class="table-no-style"><?php echo date("H:i", time()) ?> Uhr</td>
                        </tr>
                        <tr class="table-no-style">
                            <td class="table-no-style"><b>Fahrzeit</b></td>
                            <td class="table-no-style"><?php echo convertToHoursMins($rental['timeOfTravel'], '%02d Std %02d Min'); ?></td>
                        </tr>
                        <?php if (!empty($rental['commitmentBeans'])) { ?>
                            <tr class="table-no-style">
                                <td class="table-no-style"><b>Einsatz</b></td>
                                <td class="table-no-style"><?php
                                    foreach ($rental['commitmentBeans'] as $commitment)
                                        echo $commitment['name'] . " ";
                                    ?></td>
                            </tr>
                        <?php } ?>
                        <?php if (!empty($rental['promotionBeforeBean'])) { ?>
                            <tr class="table-no-style">
                                <td class="table-no-style"><b>Aktion</b></td>
                                <td class="table-no-style"><?php echo $rental['promotionBeforeBean']['name']; ?></td>
                            </tr>
                        <?php } ?>
                        <?php if (!empty($rental['pricePaidBefore'])) { ?>
                            <tr class="table-no-style">
                                <td class="table-no-style"><b>Bezahlt</b></td>
                                <td class="table-no-style">
                                    <b>&euro; <?php echo money_format("%i", $rental['pricePaidBefore']); ?></b></td>
                            </tr>
                        <?php } ?>
                        <tr class="table-no-style">
                            <td class="table-no-style"><b>Preis</b></td>
                            <td class="table-no-style">
                                <b>&euro; <?php echo money_format("%i", $rental['priceCalculatedAfter']); ?></b></td>
                        </tr>
                    </table>
                </td>
                <td class="table-no-style" valign="top">
                    <img src="<?php echo $rental['boatBean']['pictureUrl'] ?>" width="400px">
                </td>
            </tr>
        </table>
        <hr/>
        <div>
            <p class="text-small">Bootsvermietung EPPEL, Christiane Ahammer</p>
            <p class="text-small">Wagramerstr. 48a, 1220 Wien</p>
            <p class="text-small">Tel: +43 1 2633530</p>
            <p class="text-small">Mail: <a href="mailto:office@eppel-boote.at">office@eppel-boote.at</a></p>
            <p class="text-small">Web: <a href="https://www.eppel-boote.at">www.eppel-boote.at</a></p>
            <p class="text-small">ATU63304105</p>
        </div>
        <div align="center">
            <img class="image-wave" src="welle.png"/>
        </div>
        </body>
        </html>
    <?php }
} ?>

<?php

function convertToHoursMins($time, $format = '%02d:%02d')
{
    if ($time < 1) {
        return;
    }
    $hours = floor($time / 60);
    $minutes = ($time % 60);
    return sprintf($format, $hours, $minutes);
}

?>