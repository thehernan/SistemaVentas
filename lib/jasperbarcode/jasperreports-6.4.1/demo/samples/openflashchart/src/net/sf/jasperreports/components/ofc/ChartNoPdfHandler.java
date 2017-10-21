/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2016 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.components.ofc;

import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.export.GenericElementPdfHandler;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterContext;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class ChartNoPdfHandler extends BaseChartHandler implements GenericElementPdfHandler
{
	public void exportElement(JRPdfExporterContext exporterContext, JRGenericPrintElement element)
	{
		JRPdfExporter exporter = (JRPdfExporter)exporterContext.getExporterRef();
		
		JRPrintText text = getTextElementReplacement(exporterContext, element);
		
		try
		{
			exporter.exportText(text);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
