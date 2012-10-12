<?xml version="1.0"?>


<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" indent="no" />

	<!--begin template rule -->

	<!--pattern -->
	<xsl:template match="/">
		<!--action -->
		<html>
			<head><title>Bike Review</title></head>
			<body>
				<h2>Bike Review</h2>
				<xsl:apply-templates />
				<xsl:variable name="brandOccur" select="'no'" />

				<xsl:choose>
					<xsl:when test="$brandOccur = yes">
					</xsl:when>
				</xsl:choose>
			</body>
		</html>
	</xsl:template>

	<xsl:param name="brandName" select="'default value'" />
	<xsl:template match="brand">

		<xsl:variable name="currManufacturer" select="manufacturer"></xsl:variable>
		<table width="100%">
			<tr>
				<td>
					<xsl:if test="$brandName = $currManufacturer">
						<xsl:apply-templates select="model" />
						<xsl:variable name="brandOccur" select="'yes'" />
					</xsl:if>
				</td>
			</tr>
		</table>
	</xsl:template>

	<xsl:template match="model">
		<table border="1" width="100%">
			<tr>
				<td width="100%" bgcolor="#FF9966">
					<strong>


						<xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyz'" />
						<xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />
						<xsl:value-of select="translate(name, $smallcase, $uppercase)" />
					</strong>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<br />
					Price:
					<xsl:value-of select="price" disable-output-escaping="yes"/>
					<br />
					Year:
					<xsl:value-of select="year" disable-output-escaping="yes"/>
					<br />
					<br />
					<strong>Frame</strong>
					<xsl:apply-templates select="frame" />
					<br />
					<br />
					<strong>Fork</strong>
					<xsl:value-of select="fork/material" disable-output-escaping="yes"/>
					<br />
					<br />
					<strong>Components:</strong>
					<br />
					<xsl:apply-templates select="components" />
					<br />
					<br />
					<strong>Rating:</strong>
					<xsl:apply-templates select="rating" />
					<br />
				</td>
			</tr>
		</table>
	</xsl:template>

	<xsl:template match="frame">
		<xsl:value-of select="material" disable-output-escaping="yes"/>
		,
		<br></br>
		Size:
		<xsl:apply-templates select="size" />
		<br />
	</xsl:template>

	<xsl:template match="size">
		<xsl:value-of select="." disable-output-escaping="yes"/>
		,
	</xsl:template>

	<xsl:template match="components">
		<xsl:value-of select="@name" />
		:
		<xsl:value-of select="." disable-output-escaping="yes"/>
		<br />
	</xsl:template>

	<xsl:template match="rating">
		<xsl:value-of select="current" disable-output-escaping="yes"/>
		/
		<xsl:value-of select="max" disable-output-escaping="yes"/>
		:
		<xsl:value-of select="votes" disable-output-escaping="yes"/>
		<br />
	</xsl:template>
	<!--end template rule -->

</xsl:stylesheet>