from django.db import models

class SectorType(models.Model):
	pass

class Sector(models.Model):
	type = models.ForeignKey('SectorType', related_name='sectors')
        lat = models.DecimalField(max_digits=4, decimal_places=2)
        lon = models.DecimalField(max_digits=4, decimal_places=1)

class Player(models.Model):
	nick = models.CharField(max_length=255)
	account = models.CharField(max_length=255)
	lat = models.DecimalField(max_digits=10, decimal_places=8)
	lon = models.DecimalField(max_digits=11, decimal_places=8)
	sector = models.ForeignKey('Sector', related_name='players')
	health = models.IntegerField(default = 100)

#52.407959599999998, 16.908001200000001
