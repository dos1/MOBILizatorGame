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
	exp = models.IntegerField(default = 0)
	level = models.IntegerField(default = 1)

class Attack(models.Model):
	attacker = models.ForeignKey('Player', related_name='attacks_out')
	attacked = models.ForeignKey('Player', related_name='attacks_in')
	ATTACK_STATUSES = (
		(0, "pending"),
		(1, "accepted"),
		(2, "missed"),
		(3, "out of range"),
		(4, "critical")
	)
	amount = models.IntegerField(default = 20)
	status = models.IntegerField(choices = ATTACK_STATUSES, default = 0)
	#attacker_recieved = models.BooleanField(default = False)
	#attacked_recieved = models.BooleanField(default = False)

#52.407959599999998, 16.908001200000001
