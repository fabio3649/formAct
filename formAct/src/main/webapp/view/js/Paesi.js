$(document).ready(function() {
	
	var paesi = [
		'Algeria','Angola','Antigua and Barbuda','Argentina','Albania','Andorra','Armenia','Austria','Azerbaijan',
		'Armenia','Azerbaijan',
		'Benin','Botswana','Burkina Faso','Burundi','Bahamas','Barbados','Belize','Bolivia','Brazil',
		'Belarus','Belgium','Bosnia and Herzegovina','Bulgaria','Bahrain','Bangladesh','Bhutan','Brunei',
		'Cote d\'Ivoire','Cabo Verde','Cameroon','Central African Republic','Chad','Comoros','Canada',
		'Costa Rica','Cuba', 'Chile','Colombia','Croatia','Cyprus','Czech Republic','Cambodia','China','Cyprus',
		'Djibouti','Democratic Republic of the Congo','Dominica','Dominican Republic','Denmark',
		'Egypt','Equatorial Guinea','Eritrea','Ethiopia','El Salvador','Ecuador','Estonia',
		'Finland','France',
		'Gabon','Gambia','Ghana','Guinea','Guinea Bissau','Grenada','Guatemala','Guyana','Georgia','Germany',
		'Greece','Georgia',
		'Haiti','Honduras',
		'Iceland','Ireland','Italy','India','Indonesia','Iran','Iraq','Israel',
		'Jamaica','Japan','Jordan',
		'Kenya','Kazakhstan','Kosovo','Kazakhstan','Kuwait','Kyrgyzstan',
		'Lesotho','Liberia','Libya','Latvia','Liechtenstein','Lithuania','Luxembourg','Laos','Lebanon',
		'Madagascar','Malawi','Mali','Mauritania','Mauritius','Morocco','Mozambique','Mexico','Macedonia',
		'Malta','Moldova','Monaco','Montenegro','Malaysia','Maldives','Mongolia','Myanmar',
		'Namibia','Niger','Nigeria','Nicaragua','Netherlands','Norway','Nepal','North Korea',
		'Oman',
		'Panama','Paraguay','Peru','Poland','Portugal','Pakistan','Palestine','Philippines',
		'Qatar',
		'Rwanda','Republic of the Congo','Romania','Russia','Russia',
		'Sao Tome and Principe','Senegal','Seychelles','Sierra Leone','Somalia','South Africa','South Sudan',
		'Sudan','Swaziland','Saint Kitts and Nevis','Saint Lucia','Saint Vincent and the Grenadines','Suriname',
		'San Marino','Serbia','Slovakia','Slovenia','Spain','Sweden','Switzerland','Saudi Arabia','Singapore',
		'South Korea','Sri Lanka','Syria',
		'Tanzania','Togo','Tunisia','Trinidad and Tobago','Turkey','Taiwan','Tajikistan','Thailand',
		'Timor Leste','Turkey','Turkmenistan',
		'Uganda','United States of America','Uruguay','Ukraine','United Kingdom','United Arab Emirates','Uzbekistan',
		'Venezuela','Vatican City','Vietnam',
		'Yemen',
		'Zambia','Zimbabwe'
	];
	
	toStringSelectPaese (paesi);
	
	
	function toStringSelectPaese (paesi) {
		
	    var paeseSelezionato = $("#paeseSelezionato").val();
		var selectPaese = '<select name=\"country\" id=\"country\">';
		selectPaese += '<option value=\"\">Seleziona un paese</opion>';
		for (var i = 0; i < paesi.length; i++) {
			selectPaese += '<option value=\"' + paesi[i] + '\"';
			if (paesi[i] === paeseSelezionato) {
				selectPaese += ' selected';
			}
			selectPaese += '>' + paesi[i] + '</option>';
		}
		selectPaese += '</select>';
		
		$("#countryDiv").append(selectPaese);
	}
});










