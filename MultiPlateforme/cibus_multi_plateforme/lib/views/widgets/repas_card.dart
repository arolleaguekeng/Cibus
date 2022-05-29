import 'dart:ffi';

import 'package:cibus_multi_plateforme/views/Activities/restaurant_details.dart';
import 'package:flutter/material.dart';



/// Defines the title font used for [ListTile] descendants of a [ListTileTheme].
///
/// List tiles that appear in a [Drawer] use the theme's [TextTheme.bodyText1]
/// text style, which is a little smaller than the theme's [TextTheme.subtitle1]
/// text style, which is used by default.
enum ListTileStyle {
  /// Use a title font that's appropriate for a [ListTile] in a list.
  list,

  /// Use a title font that's appropriate for a [ListTile] that appears in a [Drawer].
  drawer,
}

/// Where to place the control in widgets that use [ListTile] to position a
/// control next to a label.
///
/// See also:
///
///  * [CheckboxListTile], which combines a [ListTile] with a [Checkbox].
///  * [RadioListTile], which combines a [ListTile] with a [Radio] button.
///  * [SwitchListTile], which combines a [ListTile] with a [Switch].
///  * [ExpansionTile], which combines a [ListTile] with a button that expands
///    or collapses the tile to reveal or hide the children.
enum ListTileControlAffinity {
  /// Position the control on the leading edge, and the secondary widget, if
  /// any, on the trailing edge.
  leading,

  /// Position the control on the trailing edge, and the secondary widget, if
  /// any, on the leading edge.
  trailing,

  /// Position the control relative to the text in the fashion that is typical
  /// for the current platform, and place the secondary widget on the opposite
  /// side.
  platform,
}

class RepasCard extends StatelessWidget {


  /// Creates a list tile.
  ///
  /// If [isThreeLine] is true, then [subtitle] must not be null.
  ///
  /// Requires one of its ancestors to be a [Material] widget.
  const RepasCard({
    Key? key,
    this.leading,
    this.title,
    this.subtitle,
    this.trailing,
    this.isThreeLine = false,
    this.dense,
    this.visualDensity,
    this.shape,
    this.style,
    this.selectedColor,
    this.iconColor,
    this.textColor,
    this.contentPadding,
    this.enabled = true,
    this.onTap,
    this.onLongPress,
    this.mouseCursor,
    this.selected = false,
    this.focusColor,
    this.hoverColor,
    this.focusNode,
    this.autofocus = false,
    this.tileColor,
    this.selectedTileColor,
    this.enableFeedback,
    this.horizontalTitleGap,
    this.minVerticalPadding,
    // this.minLeadingWidth,
    this.repas_id,
    this.restaurant_id,
    this.nom,
    this.image,
    this.description,
    this.prix,
    this.rating
  }) : assert(isThreeLine != null),
        assert(enabled != null),
        assert(selected != null),
        assert(autofocus != null),
        assert(!isThreeLine || subtitle != null),
        super(key: key);

  /// A widget to display before the title.
  ///
  /// Typically an [Icon] or a [CircleAvatar] widget.
  final Widget? leading;

  /// The primary content of the list tile.
  ///
  /// Typically a [Text] widget.
  ///
  /// This should not wrap. To enforce the single line limit, use
  /// [Text.maxLines].
  final Widget? title;

  /// Additional content displayed below the title.
  ///
  /// Typically a [Text] widget.
  ///
  /// If [isThreeLine] is false, this should not wrap.
  ///
  /// If [isThreeLine] is true, this should be configured to take a maximum of
  /// two lines. For example, you can use [Text.maxLines] to enforce the number
  /// of lines.
  ///
  /// The subtitle's default [TextStyle] depends on [TextTheme.bodyText2] except
  /// [TextStyle.color]. The [TextStyle.color] depends on the value of [enabled]
  /// and [selected].
  ///
  /// When [enabled] is false, the text color is set to [ThemeData.disabledColor].
  ///
  /// When [selected] is false, the text color is set to [ListTileTheme.textColor]
  /// if it's not null and to [TextTheme.caption]'s color if [ListTileTheme.textColor]
  /// is null.
  final Widget? subtitle;

  /// A widget to display after the title.
  ///
  /// Typically an [Icon] widget.
  ///
  /// To show right-aligned metadata (assuming left-to-right reading order;
  /// left-aligned for right-to-left reading order), consider using a [Row] with
  /// [CrossAxisAlignment.baseline] alignment whose first item is [Expanded] and
  /// whose second child is the metadata text, instead of using the [trailing]
  /// property.
  final Widget? trailing;

  /// Whether this list tile is intended to display three lines of text.
  ///
  /// If true, then [subtitle] must be non-null (since it is expected to give
  /// the second and third lines of text).
  ///
  /// If false, the list tile is treated as having one line if the subtitle is
  /// null and treated as having two lines if the subtitle is non-null.
  ///
  /// When using a [Text] widget for [title] and [subtitle], you can enforce
  /// line limits using [Text.maxLines].
  final bool isThreeLine;

  /// Whether this list tile is part of a vertically dense list.
  ///
  /// If this property is null then its value is based on [ListTileTheme.dense].
  ///
  /// Dense list tiles default to a smaller height.
  final bool? dense;

  /// Defines how compact the list tile's layout will be.
  ///
  /// {@macro flutter.material.themedata.visualDensity}
  ///
  /// See also:
  ///
  ///  * [ThemeData.visualDensity], which specifies the [visualDensity] for all
  ///    widgets within a [Theme].
  final VisualDensity? visualDensity;

  /// {@template flutter.material.ListTile.shape}
  /// Defines the tile's [InkWell.customBorder] and [Ink.decoration] shape.
  /// {@endtemplate}
  ///
  /// If this property is null then [ListTileThemeData.shape] is used. If that
  /// is also null then a rectangular [Border] will be used.
  ///
  /// See also:
  ///
  /// * [ListTileTheme.of], which returns the nearest [ListTileTheme]'s
  ///   [ListTileThemeData].
  final ShapeBorder? shape;

  /// Defines the color used for icons and text when the list tile is selected.
  ///
  /// If this property is null then [ListTileThemeData.selectedColor]
  /// is used. If that is also null then [ColorScheme.primary] is used.
  ///
  /// See also:
  ///
  /// * [ListTileTheme.of], which returns the nearest [ListTileTheme]'s
  ///   [ListTileThemeData].
  final Color? selectedColor;

  /// Defines the default color for [leading] and [trailing] icons.
  ///
  /// If this property is null then [ListTileThemeData.iconColor] is used.
  ///
  /// See also:
  ///
  /// * [ListTileTheme.of], which returns the nearest [ListTileTheme]'s
  ///   [ListTileThemeData].
  final Color? iconColor;

  /// Defines the default color for the [title] and [subtitle].
  ///
  /// If this property is null then [ListTileThemeData.textColor] is used. If that
  /// is also null then [ColorScheme.primary] is used.
  ///
  /// See also:
  ///
  /// * [ListTileTheme.of], which returns the nearest [ListTileTheme]'s
  ///   [ListTileThemeData].
  final Color? textColor;

  /// Defines the font used for the [title].
  ///
  /// If this property is null then [ListTileThemeData.style] is used. If that
  /// is also null then [ListTileStyle.list] is used.
  ///
  /// See also:
  ///
  /// * [ListTileTheme.of], which returns the nearest [ListTileTheme]'s
  ///   [ListTileThemeData].
  final ListTileStyle? style;

  /// The tile's internal padding.
  ///
  /// Insets a [ListTile]'s contents: its [leading], [title], [subtitle],
  /// and [trailing] widgets.
  ///
  /// If null, `EdgeInsets.symmetric(horizontal: 16.0)` is used.
  final EdgeInsetsGeometry? contentPadding;

  /// Whether this list tile is interactive.
  ///
  /// If false, this list tile is styled with the disabled color from the
  /// current [Theme] and the [onTap] and [onLongPress] callbacks are
  /// inoperative.
  final bool enabled;

  /// Called when the user taps this list tile.
  ///
  /// Inoperative if [enabled] is false.
  final GestureTapCallback? onTap;

  /// Called when the user long-presses on this list tile.
  ///
  /// Inoperative if [enabled] is false.
  final GestureLongPressCallback? onLongPress;

  /// {@template flutter.material.ListTile.mouseCursor}
  /// The cursor for a mouse pointer when it enters or is hovering over the
  /// widget.
  ///
  /// If [mouseCursor] is a [MaterialStateProperty<MouseCursor>],
  /// [MaterialStateProperty.resolve] is used for the following [MaterialState]s:
  ///
  ///  * [MaterialState.selected].
  ///  * [MaterialState.disabled].
  /// {@endtemplate}
  ///
  /// If null, then the value of [ListTileThemeData.mouseCursor] is used. If
  /// that is also null, then [MaterialStateMouseCursor.clickable] is used.
  ///
  /// See also:
  ///
  ///  * [MaterialStateMouseCursor], which can be used to create a [MouseCursor]
  ///    that is also a [MaterialStateProperty<MouseCursor>].
  final MouseCursor? mouseCursor;

  /// If this tile is also [enabled] then icons and text are rendered with the same color.
  ///
  /// By default the selected color is the theme's primary color. The selected color
  /// can be overridden with a [ListTileTheme].
  ///
  /// {@tool dartpad}
  /// Here is an example of using a [StatefulWidget] to keep track of the
  /// selected index, and using that to set the `selected` property on the
  /// corresponding [ListTile].
  ///
  /// ** See code in examples/api/lib/material/list_tile/list_tile.selected.0.dart **
  /// {@end-tool}
  final bool selected;

  /// The color for the tile's [Material] when it has the input focus.
  final Color? focusColor;

  /// The color for the tile's [Material] when a pointer is hovering over it.
  final Color? hoverColor;

  /// {@macro flutter.widgets.Focus.focusNode}
  final FocusNode? focusNode;

  /// {@macro flutter.widgets.Focus.autofocus}
  final bool autofocus;

  /// {@template flutter.material.ListTile.tileColor}
  /// Defines the background color of `ListTile` when [selected] is false.
  ///
  /// When the value is null, the `tileColor` is set to [ListTileTheme.tileColor]
  /// if it's not null and to [Colors.transparent] if it's null.
  /// {@endtemplate}
  final Color? tileColor;

  /// Defines the background color of `ListTile` when [selected] is true.
  ///
  /// When the value if null, the `selectedTileColor` is set to [ListTileTheme.selectedTileColor]
  /// if it's not null and to [Colors.transparent] if it's null.
  final Color? selectedTileColor;


  final bool? enableFeedback;


  final double? horizontalTitleGap;


  final double? minVerticalPadding;


  final String? repas_id;
  final String? restaurant_id;
  final String? nom;
  final String? image;
  final String? description;
  final double? prix;
  final double? rating;

  static Iterable<Widget> divideTiles({ BuildContext? context, required Iterable<Widget> tiles, Color? color }) {
    assert(tiles != null);
    assert(color != null || context != null);
    tiles = tiles.toList();

    if (tiles.isEmpty || tiles.length == 1) {
      return tiles;
    }

    Widget wrapTile(Widget tile) {
      return DecoratedBox(
        position: DecorationPosition.foreground,
        decoration: BoxDecoration(
          border: Border(
            bottom: Divider.createBorderSide(context, color: color),
          ),
        ),
        child: tile,
      );
    }

    return <Widget>[
      ...tiles.take(tiles.length - 1).map(wrapTile),
      tiles.last,
    ];
  }

  Color? _iconColor(ThemeData theme, ListTileThemeData tileTheme) {
    if (!enabled)
      return theme.disabledColor;

    if (selected) {
      return selectedColor ?? tileTheme.selectedColor ?? theme.listTileTheme.selectedColor ?? theme.colorScheme.primary;
    }

    final Color? color = iconColor ?? tileTheme.iconColor ?? theme.listTileTheme.iconColor;
    if (color != null)
      return color;

    switch (theme.brightness) {
      case Brightness.light:
      // For the sake of backwards compatibility, the default for unselected
      // tiles is Colors.black45 rather than colorScheme.onSurface.withAlpha(0x73).
        return Colors.black45;
      case Brightness.dark:
        return null; // null - use current icon theme color
    }
  }

  Color? _textColor(ThemeData theme, ListTileThemeData tileTheme, Color? defaultColor) {
    if (!enabled)
      return theme.disabledColor;

    if (selected) {
      return selectedColor ?? tileTheme.selectedColor ?? theme.listTileTheme.selectedColor ?? theme.colorScheme.primary;
    }

    return textColor ?? tileTheme.textColor ?? theme.listTileTheme.textColor ?? defaultColor;
  }

  bool _isDenseLayout(ThemeData theme, ListTileThemeData tileTheme) {
    return dense ?? tileTheme.dense ?? theme.listTileTheme.dense ?? false;
  }


  TextStyle _subtitleTextStyle(ThemeData theme, ListTileThemeData tileTheme) {
    final TextStyle textStyle = theme.textTheme.bodyText2!;
    final Color? color = _textColor(theme, tileTheme, theme.textTheme.caption!.color);
    return _isDenseLayout(theme, tileTheme)
        ? textStyle.copyWith(color: color, fontSize: 12.0)
        : textStyle.copyWith(color: color);
  }

  TextStyle _trailingAndLeadingTextStyle(ThemeData theme, ListTileThemeData tileTheme) {
    final TextStyle textStyle = theme.textTheme.bodyText2!;
    final Color? color = _textColor(theme, tileTheme, textStyle.color);
    return textStyle.copyWith(color: color);
  }

  Color _tileBackgroundColor(ThemeData theme, ListTileThemeData tileTheme) {
    final Color? color = selected
        ? selectedTileColor ?? tileTheme.selectedTileColor ?? theme.listTileTheme.selectedTileColor
        : tileColor ?? tileTheme.tileColor ?? theme.listTileTheme.tileColor;
    return color ?? Colors.transparent;
  }



  @override
  Widget build(BuildContext context) {
    final ThemeData theme = Theme.of(context);
    final ListTileThemeData tileTheme = ListTileTheme.of(context);
    final IconThemeData iconThemeData = IconThemeData(color: _iconColor(theme, tileTheme));
    final Set<MaterialState> states = <MaterialState>{
      if (!enabled || (onTap == null && onLongPress == null)) MaterialState.disabled,
      if (selected) MaterialState.selected,
    };
    final MouseCursor effectiveMouseCursor = MaterialStateProperty.resolveAs<MouseCursor?>(mouseCursor, states)
        ?? tileTheme.mouseCursor?.resolve(states)
        ?? MaterialStateMouseCursor.clickable.resolve(states);

    return InkWell(
      customBorder: shape ?? tileTheme.shape,
      onTap: enabled ? onTap : null,
      onLongPress: enabled ? onLongPress : null,
      mouseCursor: effectiveMouseCursor,
      canRequestFocus: enabled,
      focusNode: focusNode,
      focusColor: focusColor,
      hoverColor: hoverColor,
      autofocus: autofocus,
      enableFeedback: enableFeedback ?? tileTheme.enableFeedback ?? true,
      child: Container(
        margin: EdgeInsets.symmetric(horizontal: 10, vertical: 10),
        width: MediaQuery.of(context).size.width,
        height: 100,
        decoration: BoxDecoration(
          color: Colors.black,
          borderRadius: BorderRadius.circular(15),
          boxShadow: [
            BoxShadow(
              color: Colors.black.withOpacity(0.6),
              offset: const Offset(
                0.0,
                10.0,
              ),
              blurRadius: 10.0,
              spreadRadius: -6.0,
            ),
          ],
          image: DecorationImage(
            colorFilter: ColorFilter.mode(
              Colors.black.withOpacity(0.4),
              BlendMode.multiply,
            ),
            image: NetworkImage(image!),
            fit: BoxFit.cover,
          ),
        ),
        child: Stack(
          children: [
            Align(
              alignment: Alignment.center,
              child: Padding(
                padding: const EdgeInsets.symmetric(horizontal: 5.0),
                child: Text(
                  nom!,
                  style: const TextStyle(
                    fontSize: 30,
                  ),
                  overflow: TextOverflow.ellipsis,
                  maxLines: 2,
                  textAlign: TextAlign.center,
                ),
              ),
            ),
            Align(
              alignment: Alignment.bottomCenter,
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 100.0),
                child: Text(
                  description!,
                  style: const TextStyle(
                      fontSize: 18,
                      color: Colors.amberAccent
                  ),
                  overflow: TextOverflow.ellipsis,
                  maxLines: 4,
                  textAlign: TextAlign.center,
                ),
              ),
            ),
            Align(
              alignment: Alignment.bottomLeft,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Container(
                    padding: EdgeInsets.all(5),
                    margin: EdgeInsets.all(10),
                    decoration: BoxDecoration(
                      color: Colors.black.withOpacity(0.4),
                      borderRadius: BorderRadius.circular(15),
                    ),
                    child: Row(
                      children: [
                        const Icon(
                          Icons.favorite_border,
                          color: Colors.yellow,
                          size: 25,
                        ),
                        const SizedBox(width: 7),
                        Text(rating!.toString()),
                      ],
                    ),
                  ),
                  Container(
                    padding: const EdgeInsets.all(5),
                    margin: const EdgeInsets.all(10),
                    decoration: BoxDecoration(
                      color: Colors.black.withOpacity(0.4),
                      borderRadius: BorderRadius.circular(15),
                    ),
                    child: Row(
                      children: [
                        const Icon(
                          Icons.schedule,
                          color: Colors.yellow,
                          size: 18,
                        ),
                        const SizedBox(width: 7),
                        Text(description!),
                      ],
                    ),
                  )
                ],
              ),
            ),
          ],
        ),

      ),

    );

  }
}

// Identifies the children of a _ListTileElement.
enum _ListTileSlot {
  nom,
  description,
  rating,
  cookTime,
  thumbnailUrl,
}

Iterable<_ListTileSlot> get slots => _ListTileSlot.values;
@override
bool hitTestSelf(Offset position) => true;

