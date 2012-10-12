<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/TR/REC-html40">
	<xsl:output method="html" indent="no" />

	<!--begin template rule -->

	<!--pattern -->

	<xsl:template match="/">

		<xsl:apply-templates />

	</xsl:template>

	<xsl:template match="brand">
		<xsl:value-of select="manufacturer" />
	</xsl:template>
	<!--end template rule -->

</xsl:stylesheet>