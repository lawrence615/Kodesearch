package com.kodesearch.external;

import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;

/**
 * The manager of roboto typefaces.
 * 
 * @author Evgeny Shishkin
 */
public class RobotoTypefaceManager {

	/*
	 * Available values ​​for the "typeface" attribute.
	 */
	private final static int ROBOTO_THIN = 0;
	private final static int ROBOTO_THIN_ITALIC = 1;
	private final static int ROBOTO_LIGHT = 2;
	private final static int ROBOTO_LIGHT_ITALIC = 3;
	private final static int ROBOTO_REGULAR = 4;
	private final static int ROBOTO_ITALIC = 5;
	private final static int ROBOTO_MEDIUM = 6;
	private final static int ROBOTO_MEDIUM_ITALIC = 7;
	private final static int ROBOTO_BOLD = 8;
	private final static int ROBOTO_BOLD_ITALIC = 9;
	private final static int ROBOTO_BLACK = 10;
	private final static int ROBOTO_BLACK_ITALIC = 11;
	private final static int ROBOTO_CONDENSED_LIGHT = 12;
	private final static int ROBOTO_CONDENSED_LIGHT_ITALIC = 13;
	private final static int ROBOTO_CONDENSED_REGULAR = 14;
	private final static int ROBOTO_CONDENSED_ITALIC = 15;
	private final static int ROBOTO_CONDENSED_BOLD = 16;
	private final static int ROBOTO_CONDENSED_BOLD_ITALIC = 17;
	private final static int ROBOTO_SLAB_THIN = 18;
	private final static int ROBOTO_SLAB_LIGHT = 19;
	private final static int ROBOTO_SLAB_REGULAR = 20;
	private final static int ROBOTO_SLAB_BOLD = 21;
	/**
	 * Array of created typefaces for later reused.
	 */
	private final static SparseArray<Typeface> mTypefaces = new SparseArray<Typeface>(
			20);

	/**
	 * Obtain typeface.
	 * 
	 * @param context
	 *            The Context the widget is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param typefaceValue
	 *            The value of "typeface" attribute
	 * @return specify {@link Typeface}
	 * @throws IllegalArgumentException
	 *             if unknown `typeface` attribute value.
	 */
	public static Typeface obtaintTypeface(Context context, int typefaceValue)
			throws IllegalArgumentException {
		Typeface typeface = mTypefaces.get(typefaceValue);
		if (typeface == null) {
			typeface = createTypeface(context, typefaceValue);
			mTypefaces.put(typefaceValue, typeface);
		}
		return typeface;
	}

	/**
	 * Create typeface from assets.
	 * 
	 * @param context
	 *            The Context the widget is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param typefaceValue
	 *            The value of "typeface" attribute
	 * @return Roboto {@link Typeface}
	 * @throws IllegalArgumentException
	 *             if unknown `typeface` attribute value.
	 */
	private static Typeface createTypeface(Context context, int typefaceValue)
			throws IllegalArgumentException {
		Typeface typeface;
		switch (typefaceValue) {
		case ROBOTO_THIN:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Thin.ttf");
			break;
		case ROBOTO_THIN_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-ThinItalic.ttf");
			break;
		case ROBOTO_LIGHT:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Light.ttf");
			break;
		case ROBOTO_LIGHT_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-LightItalic.ttf");
			break;
		case ROBOTO_REGULAR:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Regular.ttf");
			break;
		case ROBOTO_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Italic.ttf");
			break;
		case ROBOTO_MEDIUM:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Medium.ttf");
			break;
		case ROBOTO_MEDIUM_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-MediumItalic.ttf");
			break;
		case ROBOTO_BOLD:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Bold.ttf");
			break;
		case ROBOTO_BOLD_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-BoldItalic.ttf");
			break;
		case ROBOTO_BLACK:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Black.ttf");
			break;
		case ROBOTO_BLACK_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-BlackItalic.ttf");
			break;
		case ROBOTO_CONDENSED_LIGHT:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoCondensed-Light.ttf");
			break;
		case ROBOTO_CONDENSED_LIGHT_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoCondensed-LightItalic.ttf");
			break;
		case ROBOTO_CONDENSED_REGULAR:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoCondensed-Regular.ttf");
			break;
		case ROBOTO_CONDENSED_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoCondensed-Italic.ttf");
			break;
		case ROBOTO_CONDENSED_BOLD:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoCondensed-Bold.ttf");
			break;
		case ROBOTO_CONDENSED_BOLD_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoCondensed-BoldItalic.ttf");
			break;
		case ROBOTO_SLAB_THIN:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoSlab-Thin.ttf");
			break;
		case ROBOTO_SLAB_LIGHT:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoSlab-Light.ttf");
			break;
		case ROBOTO_SLAB_REGULAR:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoSlab-Regular.ttf");
			break;
		case ROBOTO_SLAB_BOLD:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoSlab-Bold.ttf");
			break;
		default:
			throw new IllegalArgumentException(
					"Unknown `typeface` attribute value " + typefaceValue);
		}
		return typeface;
	}

}

